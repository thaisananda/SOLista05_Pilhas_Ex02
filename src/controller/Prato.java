package Controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Prato extends Thread {
    private int id;
    private Semaphore semaforoEntrega;
    private Random rand = new Random();

    public Prato(int id, Semaphore semaforoEntrega) {
        this.id = id;
        this.semaforoEntrega = semaforoEntrega;
    }

    @Override
    public void run() {
        try {
            String nomePrato = (id % 2 == 0) ? "Lasanha à Bolonhesa" : "Sopa de Cebola";
            double tempoCozimento = (id % 2 == 0)
                    ? 0.6 + rand.nextDouble() * 0.6 // 0.6 a 1.2
                    : 0.5 + rand.nextDouble() * 0.3; // 0.5 a 0.8

            int tempoCozMs = (int) (tempoCozimento * 1000);
            int etapas = tempoCozMs / 100;

            System.out.println("Prato " + id + " (" + nomePrato + ") iniciado. Tempo total: " + tempoCozimento + "s");

            for (int i = 1; i <= etapas; i++) {
                Thread.sleep(100);
                int percentual = (i * 100) / etapas;
                System.out.println("Prato " + id + ": " + percentual + "% pronto.");
            }

            System.out.println("Prato " + id + " finalizado. Aguardando entrega...");

            semaforoEntrega.acquire(); // só um pode entregar por vez

            System.out.println("Entregando prato " + id + "... (leva 0.5s)");
            Thread.sleep(500); // simula entrega
            System.out.println("Prato " + id + " entregue com sucesso!");

            semaforoEntrega.release();

        } catch (InterruptedException e) {
            System.out.println("Erro no prato " + id);
        }
    }
}
