package it.univaq.disim.lpo.unifymusic.model;

@FunctionalInterface
public interface CriterioFiltro<T> {
    boolean accetta(T elemento);

    default CriterioFiltro<T> and(CriterioFiltro<T> altro) {
        if (altro == null) {
            throw new IllegalArgumentException("Il criterio non può essere null.");
        }
        return elemento -> this.accetta(elemento) && altro.accetta(elemento);
    }

    default CriterioFiltro<T> negate() {
        return elemento -> !this.accetta(elemento);
    }

}
