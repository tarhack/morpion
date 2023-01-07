package com.codingf.morpion.tools;

import java.io.IOException;

public class Clear {
        public static void main(String... arg) throws IOException, InterruptedException {
            clear();
        }
        public static void clear() throws IOException, InterruptedException {
            var p = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
}
