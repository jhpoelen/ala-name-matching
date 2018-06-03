package au.org.ala.names.index;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A name without ranking information (but with a nomenclatural code).
 *
 * @author Doug Palmer &lt;Doug.Palmer@csiro.au&gt;
 * @copyright Copyright &copy; 2017 Atlas of Living Australia
 */
public class UnrankedScientificName extends Name<UnrankedScientificName, BareName, ScientificName> {
    public UnrankedScientificName(BareName container, NameKey key) {
        super(container, key);
    }

    /**
     * Create a scientific name
     *
     * @param stageKey The stage key
     *
     * @return The new scientific name
     */
    @Override
    ScientificName createConcept(NameKey stageKey) {
        return new ScientificName(this, stageKey);
    }

    /**
     * Create a key for this stage.
     *
     * @param instanceKey
     *
     * @return A key that corresponds to the keys used by the concept map
     */
    NameKey buildStageKey(NameKey instanceKey) {
        return instanceKey.toNameKey();
    }


    /**
     * If we have a principal, then any unranked names are reallocated to the principal
     *
     * @param taxonomy The resolving taxonomy
     * @param principal The principal scientific name
     */
    @Override
    protected void reallocateDanglingConcepts(Taxonomy taxonomy, ScientificName principal) {
        if (principal == null)
            return;
        List<ScientificName> reallocated = new ArrayList<>();
        for (ScientificName name: this.getConcepts()) {
            if (name != principal && name.getKey().isUnranked()) {
                principal.reallocate(name, taxonomy, "unrankedScientificName.reallocated.provenance");
                reallocated.add(name);
            }
        }
        this.removeConcepts(reallocated);
        taxonomy.count("count.resolve.unrankedScientificName.principal");
    }

    /**
     * Find a principal scientific name.
     * <ul>
     *     <li>If there is only one scientific name, then that is it.</li>
     *     <li>If there is only one ranked scientific name, then that is it.</li>
     *     <li>If there is more than one ranked scientific name, then choose the one with a principal with the highest score. (Or the first if there are multiple ones)</li>
     * </ul>
     * @param taxonomy The resolving taxonomy.
     *
     * @return The principal
     */
    @Override
    protected ScientificName findPrincipal(Taxonomy taxonomy) {
        List<ScientificName> names = this.getConcepts();
        if (names.isEmpty())
            return null;
        if (names.size() == 1)
            return names.get(0);
        names.sort(REVERSE_PROVIDER_SCORE_COMPARATOR);
        final int cutoff = taxonomy.getAcceptedCutoff();
        List<ScientificName> ranked = names.stream().filter(sn -> !sn.getKey().isUnranked() && sn.getPrincipal() != null && sn.getPrincipalScore() > cutoff).collect(Collectors.toList());
        if (ranked.size() == 0)
            return names.get(0);
        if (ranked.size() == 1) {
            return ranked.get(0);
        }
        taxonomy.report(IssueType.COLLISION, "unrankedScientificName.collision", this, ranked);
        final int score = ranked.stream().mapToInt(ScientificName::getPrincipalScore).max().orElse(TaxonomicElement.MIN_SCORE);
        List<ScientificName> candidates = ranked.stream().filter(sn -> sn.getPrincipalScore() == score).collect(Collectors.toList());
        if (candidates.size() > 1)
            taxonomy.report(IssueType.PROBLEM, "unrankedScientificName.collision.warn", this, candidates);
        return candidates.get(0);
    }


    /**
     * Reallocate the elements of another taxon concept to this taxon concept.
     *
     * @param element The element to reallocate
     * @param taxonomy The resolving taxonomy
     * @param reason The code for why this reallocation is happening
     */
    @Override
    public void reallocate(UnrankedScientificName element, Taxonomy taxonomy, String reason) {
        ScientificName principal = this.getPrincipal();
        taxonomy.report(IssueType.NOTE, "unrankedScientificName.reallocated", element, Arrays.asList(this));
        taxonomy.count("count.reallocate.unrankedScientificName");
        if (principal == null)
            throw new IndexBuilderException("Unable to reallocate " + element + " to " + this + " without principal");
        for (ScientificName name: element.getConcepts()) {
            principal.reallocate(name, taxonomy, reason);
        }
        element.clear(principal);
    }

    /**
     * A human readbale label for the concept
     *
     * @return The label
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        ScientificName principal = this.getPrincipal();
        TaxonConceptInstance representative = this.getRepresentative();
        builder.append("UN[");
        builder.append(this.getKey().getCode() == null ? "no code" : this.getKey().getCode().getAcronym());
        builder.append(", ");
        builder.append(this.getKey().getScientificName());
        if (principal != null) {
            builder.append(" = ");
            builder.append(principal.getKey());
        }
        if (representative != null) {
            builder.append(" = ");
            builder.append(representative.getLocator());
        }
        builder.append("]");
        return builder.toString();
    }

}
