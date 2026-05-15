package br.edu.ifsp.ads.sistema_salgados.patterns.factory;
import br.edu.ifsp.ads.sistema_salgados.model.Coxinha;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaFrango;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaCatupiry;

public class CoxinhaFactory {
    public static Coxinha obterCoxinha(String sabor) {
        return switch (sabor.toLowerCase()) {
            case "catupiry" -> new CoxinhaCatupiry();
            case "frango" -> new CoxinhaFrango();
            default -> throw new IllegalArgumentException("Sabor não cadastrado");
        };
    }
}