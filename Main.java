// i was bored asf
// 3am, 17/07/2024
// pq me vino de gusto hacer algo oldstyle

public class Main {
    public static void main(String[] args) {
        System.out.println("Mandelbrot set drawing in ascii, made by SHZTD :)\n");
        // estas variables son las que hacen la gracia grafica
        int w = 120, h = 35; // tamaño de display en la consola, w = ancho, h = altura
        int maxIter = 100; // cambialo a tu gusto (depende de cuanto quieras quemar tu CPU)

        char[] fill = {'.', ':', '-', '=', '+', '*', '#', '%', '@'}; // caracteres (robados de el famoso donut)
        char[] scr = new char[w*h]; // buffer donde se guarda el grafico

        float zm = 1.0f; // factor zoom
        float cX = 0.0f; // camara posX
        float cY = 0.0f; // camara posY

        // de la mano de wikipedia y de mi brain
        for (float x = 0; x < w; x++) {
            for (float y = 0; y < h; y++) {
                // si quereis mas precision metedle doubles
                // normaliza las coordenadas del char
                // reescala el rango (esto es para el zoom -> zm = 1.0, el rango es 3.75 xra el plano coplejo)
                // luego se traduce el pov al centro
                // finalmente añadir el offset al plano (+ cX) (la posicion de la camara)
                // explicacion para X y Y
                float x0 = (float)(x / w * (3.5 / zm) - (2.5 / zm) + cX);
                float y0 = (float)(y / h * (2.0 / zm) - (1.0 / zm) + cY);

                float x1 = 0.0f;
                float y1 = 0.0f;

                int itr = 0; // steps

                while (x1 * x1 + y1 * y1 <= 4 && itr < maxIter) { // ehm, mola xd
                    float temp = x1 * x1 - y1 * y1 + x0;
                    y1 = 2 * x1 * y1 + y0;
                    x1 = temp;
                    itr = itr + 1;
                }
                scr[(int)x + (int)y * w] = fill[itr % fill.length]; // perdon >_<
            }
        }

        // imprime la magia (le podria meter zoom y hacerlo animado xro me da palo ^_^)
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                System.out.print(scr[x + y * w]);
            }
            System.out.println();
        }
        System.out.println("\nHope u liked it, enjoy.");
    }
}