/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondeevaluaciones;

import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Menu {
    public static void main(String[] args) {
        SistemaGestion sistema = new SistemaGestion();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            sistema.mostrarOpcionesMenu(scanner);
        }
    }
}