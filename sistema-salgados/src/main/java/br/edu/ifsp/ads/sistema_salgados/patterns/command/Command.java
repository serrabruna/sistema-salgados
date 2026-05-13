package br.edu.ifsp.ads.sistema_salgados.patterns.command;

public interface Command {
    void execute();
    void undo();
}
