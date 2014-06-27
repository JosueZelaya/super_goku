/**
 *
 * @author Alexander Zelaya
 */
/*
 * Clase Matriz
 * Algoritmos Graficos
 * 
 * La clase matriz realiza las operaciones basicas que se pueden
 * aplicar a las matrices: suma, resta, multiplicacion, transpuesta e inversa;
 * 
 * Resumen de la clase
 * ATRIBUTOS
 * -filas:int
 * -columnas:int
 * -Matrix:double[][]
 * 
 * CONSTRUCTORES Y METODOS
 * + $ Matriz(numFilas:int,numColumnas:int)
 * + $ Sumar(a:Matriz,b:Matriz):Matriz
 * + $ Restar(a:Matriz,b:Matriz):Matriz
 * + $ Multiplicar(a:Matriz,b:Matriz):Matriz
 * + $ Transponer(a:Matriz):Matriz
 * + $ Identidad(r:int):Matriz
 * + $ Invertir(a:Matriz):Matriz
 * - $ prepararPivote(a:Matriz,numero:Double,fila:int);
 * + getFilas():int
 * + getColumnas():int
 * + getPosicion(a:int,b:int):Double
 * + setPosicion(numero:Double,a:int,b:int)
 */

package superGoku;

public class Matriz {

    //ATRIBUTOS DE LA CLASE
    private int filas;
    private int columnas;
    private Double[][] Matrix;
    
    
    //CONSTRUCTORES Y METODOS
    public Matriz(int numFilas,int numColumnas){
        /*
         * Es el constructor de la clase Matriz, el cual crea una matriz
         * con las dimensiones pasadas por parametro y rellena la matriz
         * con ceros.
         */
        filas = numFilas;
        columnas = numColumnas;
        //Creamos el array bidimensional con las dimensiones pasadas or parametro
        Matrix= new Double[filas][columnas];
        //Utilizamos dos for anidados para recorrer cada posicion dentro del array y asignar un cero
        for(int i=0;i<Matrix.length;i++){
            for(int j=0;j<Matrix[i].length;j++){
                Matrix[i][j]= 0.0;
            }
        }
     }

    //Devuelve el numero de filas de la matriz
    public int getFilas(){
        return filas;
    }

    //Devuelve el numero de columnas de la matriz
    public int getColumnas(){
        return columnas;
    }

    //Devuelve el valor del elemento que corresponde a la posicion pasada por parametro
    public Double getPosicion(int a, int b){
        return Matrix[a][b];
    }

    //Agrega el numero pasado por parametro a la matriz, en la posicion especificada
    public void setPosicion(Double numero, int a, int b){
        Matrix[a][b] = numero;
    }

    /*
     * Metodo estatico que sirve para sumar dos matrices pasadas por parametro.
     * 1) Comprueba que las matrices sean de iguales dimensiones
     * 2) Si las matrices son de iguales dimensiones se procede a sumarlas.
     * 3) Se devuelve el resultado en una nueva matriz
     */
    public static Matriz Sumar(Matriz a, Matriz b) throws MatrizFormatException{
        //Comprobando que las matrices sean de iguales dimensiones
        if(a.getFilas()!=b.getFilas() || a.getColumnas()!=b.getColumnas()){
            throw new MatrizFormatException("\nLas matricez a sumar deben ser de iguales dimenciones");
        }
        //Se crea una matriz para guardar en ella el resultado y devolverlo
        Matriz resultado = new Matriz(a.getFilas(),a.getColumnas());
        /*
         * Se procede a recorrer cada elemento de la matriz y a sumarlos
         * guardando el resultado en la matriz resultado
         */
        for(int i=0;i<a.getFilas();i++){
            for(int j=0;j<a.getColumnas();j++){
               resultado.setPosicion(a.getPosicion(i, j)+b.getPosicion(i, j), i, j);
            }
        }
        //Se devuelve la matriz con la respuesta
        return resultado;
    }

    /*
     * Metodo estatico que sirve para restar dos matrices pasadas por parametro.
     * 1) Comprueba que las matrices sean de iguales dimensiones
     * 2) Si las matrices son de iguales dimensiones se procede a restarlas.
     * 3) Se devuelve el resultado en una nueva matriz
     */
    public static Matriz Restar(Matriz a, Matriz b) throws MatrizFormatException{
        //Comprobando que las matrices sean de iguales dimensiones
        if(a.getFilas()!=b.getFilas() || a.getColumnas()!=b.getColumnas()){
            throw new MatrizFormatException("\nLas matricez a restar deben ser de iguales dimenciones");
        }
        //Se crea una matriz para guardar en ella el resultado y devolverlo
        Matriz resultado = new Matriz(a.getFilas(),a.getColumnas());
        /*
         * Se procede a recorrer cada elemento de la matriz y a restarlos
         * guardando el resultado en la matriz resultado
         */
        for(int i=0;i<a.getFilas();i++){
            for(int j=0;j<a.getColumnas();j++){
                resultado.setPosicion(a.getPosicion(i,j)- b.getPosicion(i, j), i, j);
            }
        }
         //Se devuelve la matriz con la respuesta
        return resultado;
    }

    /*
     * Metodo estatico que sirve para multiplicar dos matrices pasadas por parametro.
     * 1) Recibe una matriz A, y una matriz B
     * 2) Comprueba que el numero de columnas de A sea igual al numero de Filas de B
     * 3) Si pasa la comprobacion entonces se procede a multiplicarlas
     * 3) Se devuelve el resultado en una nueva matriz
     */
    public static Matriz Multiplicar(Matriz a, Matriz b) throws MatrizFormatException{
        Matriz respuesta = new Matriz(a.getFilas(),b.getColumnas());
        Double numero = 0.0;
        //Se comprueba que el numero de columnas de A sea igual al numero de Filas de B
        if(a.getColumnas()==b.getFilas()){
            /*
             * Se procede a efectuar la multiplicacion de las matrices, guardando el resultado
             * en una nueva matriz llamada respuesta
             */
            for(int ai=0;ai<a.getFilas();ai++){
                for(int bj=0;bj<b.getColumnas();bj++){
                    for(int aj=0; aj<a.getColumnas();aj++){
                        numero = a.getPosicion(ai, aj)* b.getPosicion(aj, bj)+numero;
                    }
                    respuesta.setPosicion(numero, ai, bj);
                    numero = 0.0;
                }
            }
        }else{
            throw new MatrizFormatException("\nLas columnas de A deben ser igual a las Filas de B para poder multiplicarse");
        }
        //Se devuelve la matriz con la respuesta
        return respuesta;
    }

    /*
     * Metodo estatico que sirve para transponer una matriz pasada por parametro.
     * Devuelve una nueva matriz con el resultado de la operacion
     */
    public static Matriz Transponer(Matriz a){
        Matriz respuesta = new Matriz(a.getColumnas(),a.getFilas());
        //Se procede a transponer la matriz y a guardar el resultado en una nueva matriz
        for(int i=0; i<a.getFilas();i++){
            for(int j=0;j<a.getColumnas();j++){
                respuesta.setPosicion(a.getPosicion(i, j), j, i);
            }
        }
        //Se devuelve la matriz con la respuesta
        return respuesta;
    }

    /*
     * Metodo estatico que sirve para obtener una matriz identidad pasando un unico
     * parametro que corresponde al numero de filas o columnas de la matriz identidad.
     * Una matriz identidad siempre es cuadrada, es por ello que el numero pasado
     * por parametro representa ya sea al numero de filas o de columnas
     * Una vez obtenida la matriz identidad devuelve el resultado en una nueva matriz
     */
    public static Matriz Identidad(int r){
        //Cuando se crea una matriz usando este constructor, automaticamente se llena con ceros
        Matriz respuesta = new Matriz(r,r);
        //Se procede a llenar la diagonal principal con 1
        for(int i=0;i<r;i++){           
            respuesta.setPosicion(1.0, i, i);            
        }
        //Se devuelve la matriz con la respuesta
        return respuesta;
    }

    /*
     * Metodo estatico que sirve para obtener la inversa de la matriz pasada por parametro
     * Utiliza el metodo de gauss-jordan sin usar determinantes.
     * Antes de intentar invertir la matriz se verifica que sea una matriz cuadrada, de lo
     * contrario se lanza una excepcion indicando el error encontrado.
     * Tambien se verifica que en el pivote no se encuentre un cero, de lo contrario
     * lanza una excepcion informando del error encontrado.
     * Si la matriz cumple con todos los requisitos del metodo de gauss-jordan entonces se
     * obtiene la matriz inversa en la que antes era la matriz identidad y se devuelve el resultado
     */
    public static Matriz Invertir(Matriz a) throws MatrizFormatException{
        //Para utilizar el metodo de gauss-jordan necesitamos utilizar la matriz identidad
        //que es la matriz donde quedara la inversa al terminar el algoritmo
        /*
         * La matriz de la izquierda representa la matriz actual, y la matriz de la derecha
         * es la matriz identidad. Las operaciones se aplican a ambas matrices de manera que
         * al terminar el algoritmo de gauss-jordan la matriz identidad ahora contiene
         * la matriz inversa de la matriz
         * x x x | 1 0 0
         * x x x | 0 1 0
         * x x x | 0 0 1
         */
        Matriz identidad = Identidad(a.getFilas());
        Double numero;
        //Se verifica que la matriz sea cuadrada antes de intentar invertir
        if(a.getFilas()==a.getColumnas()){            
            for(int d=0;d<a.getFilas();d++){
                //Se obtiene el pivote
                numero = a.getPosicion(d,d);
                //Y se procede a preparar toda la fila pivote dividiendola entre el pivote
                //De manera que el pivote al ser divido entre el mismo toma valor de 1
                a = prepararPivote(a,numero,d);
                //Se hace lo mismo a la matriz identidad                
                identidad = prepararPivote(identidad,numero,d);                
                /*
                 * A diferencia de los metodos en los cuales se hace cero solo lo que esta
                 * en la columna abajo del pivote, obteniendo una matriz triangular, para luego
                 * hacer cero todo lo que se encuentra en la columna arriba del pivote hasta
                 * obtener una matriz diagonal. En este metodo se busca obtener directamente
                 * una matriz diagonal, haciendo cero tanto lo que esta arriba del pivote
                 * como lo que se encuentra abajo del pivote. Tal operacion no afecta el resultado
                 * pero disminuye la cantidad de codigo necesario y aumenta la velocidad para calcular
                 * la matriz inversa.
                 */
                for(int i=0;i<a.getFilas();i++){
                    /* Encontramos el numero que se utilizara para hacer cero a la posicion
                     * ya sea arriba del pivote o abajo del pivote
                     */
                    numero = 0 - a.getPosicion(i, d);                   
                    for(int j=0;j<a.getFilas();j++){
                        if(i!=d){
                            /*
                             * Se realizan las operaciones haciendo cero lo que se encuentre arriba
                             * o abajo del pivote, de tal manera que se obtenga una matriz diagonal.
                             * La misma operacion que se realiza en la matriz original, debe realizarse
                             * en la matriz identidad.
                             */
                            a.setPosicion(numero* a.getPosicion(d, j) + a.getPosicion(i, j), i, j);
                            identidad.setPosicion(numero * identidad.getPosicion(d, j) + identidad.getPosicion(i, j), i, j);
                       }
                    }
                }
            }
        }else{
            throw new MatrizFormatException("\nPara invertir una matriz esta debe ser cuadrada");
        }
        /*
         * Una vez concluido el algoritmo de gauss-jordan, la matriz identidad ahora contiene
         * a la matriz inversa de la matriz original, por ello se devuelve la matriz llamda identidad
         * que ahora es la inversa.
         */
        return identidad;
    }

    
    /*
     * Metodo estatico de ambito privado que sirve para preparar la fila pivote, dividiendo la fila entre el pivote
     * de tal manera que el pivote pase a tomar el valor de 1. Tambien es en este metodo donde se
     * verifica que no se encuentre un cero en el pivote, en caso de encontrarse se lanza una excepcion.
     */
    private static Matriz prepararPivote(Matriz a,Double numero,int fila) throws MatrizFormatException{
       if(numero!=0){
            for(int i=0;i<a.getColumnas();i++){
                a.setPosicion(a.getPosicion(fila, i) / numero, fila, i);
            }
        }else{
               throw new MatrizFormatException("\nNo se pudo invertir\nSe ha encontrado un CERO en el pivote");
        }     
        return a;
    }
    
    
    /*
     * ********************** MATRICES PARA TRANSFORMACIONES *******************************************************
     */
    
    public static Matriz getMatrizEscalar(double escalaX,double escalaY){
    	Matriz escalar = new Matriz(2,2);
    	escalar.setPosicion(escalaX, 0, 0);
    	escalar.setPosicion(0.0, 0, 1);
    	escalar.setPosicion(0.0, 1, 0);
    	escalar.setPosicion(escalaY, 1, 1);
    	return escalar;    	
    }
    

   }

