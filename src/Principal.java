package Lista05;

import java.util.concurrent.Semaphore;

import Controller.Prato;

public class Principal {

    public static void main(String[] args) {
        final int TOTAL_PRATOS = 5;
        Semaphore semaforoEntrega = new Semaphore(1); // apenas 1 prato pode ser entregue por vez

        for (int i = 1; i <= TOTAL_PRATOS; i++) {
            Prato prato = new Prato(i, semaforoEntrega);
            prato.start();
        }
    }
}
