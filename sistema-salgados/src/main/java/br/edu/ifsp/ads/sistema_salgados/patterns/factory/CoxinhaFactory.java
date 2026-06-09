package br.edu.ifsp.ads.sistema_salgados.patterns.factory;
import br.edu.ifsp.ads.sistema_salgados.model.Coxinha;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaCarne;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaFrango;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaQueijo;
import br.edu.ifsp.ads.sistema_salgados.model.CoxinhaCatupiry;

public class CoxinhaFactory {
    public static Coxinha obterCoxinha(String sabor) {
        if (sabor == null) {
            throw new IllegalArgumentException("O sabor não pode ser nulo");
        }
        return switch (sabor.toLowerCase().trim()) {
            case "frango" -> new CoxinhaFrango();
            case "catupiry" -> new CoxinhaCatupiry();
            case "carne" -> new CoxinhaCarne();   
            case "queijo" -> new CoxinhaQueijo(); 
            default -> throw new IllegalArgumentException("Sabor '" + sabor + "' não cadastrado no sistema");
        };
    }
}