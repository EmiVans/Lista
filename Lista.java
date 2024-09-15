import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
/**
 *
 * <p> Clase concreta para modelar la estructura de datos Lista</p>
 * <p>Esta clase implementa una Lista genérica, es decir que es homogénea pero
 * puede tener elementos de cualquier tipo.
 */

public class Lista<T> implements Listable<T>, Coleccionable<T> {

	/* Clase interna para construir la estructura */
    private class Nodo{
	   /* Referencias a los nodos anterior y siguiente */
        public Nodo anterior, siguiente;
        /* El elemento que almacena un nodo */
        public T elemento;

        /* Unico constructor de la clase */
        public Nodo(T elemento){
            //aqui va tu codigo
            this.elemento = elemento;
            this.anterior = null;
            this.siguiente = null;
        }
        public Nodo(T elemento, Nodo anterior, Nodo siguiente){
            this.elemento=elemento;
            this.siguiente=siguiente;
            this.anterior = anterior;
        }
        
        public boolean equals(Nodo n){
            if(this.elemento == n.getElemento()){
                return true;
            }
            return false;
        }

        public void setSiguiente(Nodo siguiente){
            this.siguiente=siguiente;
        }
        public void setAnterior(Nodo anterior){
            this.anterior=anterior;
        }
        
        public T getElemento(){
            return elemento;
        }
        public Nodo getAnterior(){
            return anterior;
        }
        public Nodo getSiguiente(){
            return siguiente;
        }
        
    }
    
    
    private class IteradorLista implements Iterator{
        /* La lista a recorrer*/
        private Lista<T> lista;
        /* Elementos del centinela que recorre la lista*/
        private Lista<T>.Nodo anterior, siguiente;
        
        
        public IteradorLista(Lista<T> lista){
           this.lista = lista;
           siguiente = lista.cabeza;
           anterior = null;
        }
        @Override
        public boolean hasNext() {
            if(siguiente!=null){
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if(this.hasNext()) {
            	
                this.anterior=this.siguiente;
                this.siguiente=this.siguiente.getSiguiente();
                return this.anterior.getElemento();
             }
            return null;
        }  

        @Override
        public void remove(){
	     throw new UnsupportedOperationException("Operacion no valida");    
        }
       
    }

    /* Atributos de la lista */
    private Nodo cabeza, cola;
    private int longitud;
    //constructor omidion
    public Lista(){
        this.cabeza=null;
        this.cola=null;
        this.longitud=0;
    }
    
    public Lista(T elemento){//solo opciones
        Nodo nodo = new Nodo(elemento);
        this.cabeza=nodo;
        this.cola=nodo;
        this.longitud=1;
    }
    
    /**
     * Método que nos dice si las lista está vacía.
     * @return <code>true</code> si el conjunto está vacío, <code>false</code>
     * en otro caso.
     */
    public boolean esVacia(){
        return longitud ==0;
    }
        
    /**
     * Método para eliminar todos los elementos de una lista
     */
    public void vaciar(){
        //aqui va tu codigo
        cabeza=null;
        cola=null;
        longitud=0;
    }
    /**
     * Método para obtener el tamaño de la lista
     * @return tamanio Número de elementos de la lista.
     **/
    public int getTamanio(){
        return this.longitud; 
    }
    /**
     * Método para agregar un elemento a la lista.
     * @param elemento Objeto que se agregará a la lista.
     */
    public void agregar(T elemento) throws IllegalArgumentException{ 
    	/**if(elemento.getClass()==this.cabeza.getClass()){
    	throw	new IllegalArgumentException("Elemento no valido");
    	}*/
    	this.agregarAlFinal(elemento);
    }
        /**
     * Método para agregar al inicio un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlInicio(T elemento){
    	//aqui va tu codigo
        Nodo nodo = new Nodo(elemento);
        if(longitud==0){
            this.cabeza = nodo;
            this.cola = nodo;
        }
        else{
            nodo.setSiguiente(cabeza);
            cabeza.setAnterior(nodo);
            cabeza = nodo;
        }
        longitud++;
    }   
    /**
     * Método para agregar al final un elemento a la lista.
     * @param elemento Objeto que se agregará al inicio de la lista.
     */
    public void agregarAlFinal(T elemento){
        //aqui va tu codigo
        Nodo nodo = new Nodo(elemento);
        if(longitud==0){
            this.cabeza = nodo;
            this.cola = nodo;
        }
        else{
            nodo.setAnterior(cola);
            cola.setSiguiente(nodo);
            cola = nodo;
        }
        longitud++;
    }
    /**
     * Método para verificar si un elemento pertenece a la lista.
     * @param elemento Objeto que se va a buscar en la lista.
     * @return <code>true</code> si el elemento esta en el lista y false en otro caso.
     */
    public boolean contiene(T elemento){
        Iterator it = this.iterator();
        while(it.hasNext()) {
        	T sigElemen = (T) it.next();
        	if(sigElemen.equals(elemento)) {
        		return true;
        	}
        }
        return false;
    }
    /**
     * Método para eliminar un elemento de la lista.
     * @param elemento Objeto que se eliminara de la lista.
     */
    public void eliminar(T elemento) throws NoSuchElementException{
    	if(!this.esVacia()) {
    	Nodo aux = cabeza;
        while(aux!=null && !aux.getElemento().equals(elemento)){
            aux=aux.siguiente;
        }
         if(aux!=null){
                if (longitud==1){
                    vaciar();  
                }
                if(aux==cabeza){
                    cabeza.siguiente.anterior=null;
                    cabeza=cabeza.siguiente;
                    longitud--;
                }else if(aux==cola){
                    cola.anterior.siguiente=null;
                    cola=cola.anterior;
                    longitud--;
                }else{
                    aux.anterior.siguiente=aux.siguiente;
                    aux.siguiente.anterior=aux.anterior;
                    longitud--;
                }
            }
        }
    	}
    
    /**
     * Método que devuelve la posición en la lista que tiene la primera
     * aparición del <code> elemento</code>.
     * @param elemento El elemnto del cuál queremos saber su posición.
     * @return i la posición del elemento en la lista, -1, si no se encuentra en ésta.
     */
    public int indiceDe(T elemento){
        int Posicion=0; 
        Iterator it = this.iterator();
        while(it.hasNext()) {
        	if(it.next().equals(elemento)) {
        		return Posicion;
        	}
        	Posicion +=1;
        }
        return -1; 
    }
    
    /**
     * Método que nos dice en qué posición está un elemento en la lista
     * @param i La posición cuyo elemento deseamos conocer.
     * @return <code> elemento </code> El elemento que contiene la lista, 
     * <code>null</code> si no se encuentra
     * @throws IndexOutOfBoundsException Si el índice es < 0 o >longitud()
     */
    public T getElemento(int i)throws IndexOutOfBoundsException{
    	if(this.longitud<=i||i<0){
    		 throw new IndexOutOfBoundsException("Operacion no valida");
    	}
        Iterator it = this.iterator();
        int Posicion = 0;
        T elemento = null; 
        while(Posicion<=i){
        	elemento = (T) it.next();
        	Posicion ++;
        }
		return elemento; 
    }
    
    /**
     * Método que devuelve una copia de la lista, pero en orden inverso
     * @return Una copia con la lista al reves.
     */
    public Lista<T> reversa(){
        Lista<T> lista = new Lista<T>(); //crear lista
        for (T e:this){
            lista.agregarAlInicio(e);
        }
        return lista;
    }
    
    
    /**
     * Método que devuelve una copi exacta de la lista
     * @return la copia de la lista.
     */
    public Lista<T> copia(){
        Lista<T> lista = new Lista<T>(); //crear lista
        for (T e:this){
            lista.agregarAlFinal(e);
        }
        return lista; 
    }
    
    /**
     * Método que nos dice si una lista es igual que otra.
     * @param o objeto a comparar con la lista.
     * @return <code>true</code> si son iguales, <code>false</code> en otro caso.
     */
    public boolean equals(Object o){
    	if(o == null || this.getClass()!=o.getClass()){
    		return false;
    	}
    	else {
    		Lista LisTemp = (Lista<T>)o;
    		if(this.getTamanio()!=LisTemp.getTamanio()){
    			return false; 
    		}
    		for(int i=0; i<this.getTamanio();i++) {
    			if(!this.getElemento(i).equals(LisTemp.getElemento(i)))   {
    				return false; 
    			}
    			
    		}return true;
    	}
    }
    
    /**
     * Método que devuelve un iterador sobre la lista
     * @return java.util.Iterador -- iterador sobre la lista
     */
    @Override
    public java.util.Iterator iterator(){
        return new IteradorLista(this);
    }
    
      
    
    public static <T extends Comparable<T>> Lista <T> mergesort(Lista<T> lista){
    	if(lista.getTamanio()<=1) {
    		return lista;
    	}
        Lista<T> aux1 = new Lista<>();
        Lista<T> aux2 = new Lista<>();
        Iterator it = lista.iterator();
        int longi = lista.getTamanio();
        int recorridos = 0; 
        while(it.hasNext()) {
        	if(recorridos<longi/2 ) {
        		aux1.agregarAlFinal((T) it.next());
        		recorridos++;
        	}else {
        		aux2.agregarAlFinal((T) it.next());
        	}
        }
        aux1=mergesort(aux1);
        aux2=mergesort(aux2);
        return merge(aux1,aux2);
        
    }


    public static <T extends Comparable<T>> Lista <T> merge(Lista<T> l1,Lista<T> l2){
        //aqui va tu codigo
    	Lista<T> auxiliar = new Lista<T>();
    	Iterator it1 = l1.iterator();
    	Iterator it2 = l2.iterator(); 
        T e1 =(T)it1.next();
        T e2 = (T)it2.next();
        while(e1 !=null && e2 !=null) {
        
        	if(e1.compareTo(e2)<0) {
        		auxiliar.agregarAlFinal(e1);
        		e1 = (T) it1.next();
        	} else{
        		auxiliar.agregarAlFinal(e2);
        		e2= (T) it2.next();
        	}
    
        }
        while(it1.hasNext() || e1!=null){
        	auxiliar.agregarAlFinal(e1);
        	e1 = (T)it1.next();
        }
        while(it2.hasNext()|| e2 !=null) {
        	auxiliar.agregarAlFinal(e2);
        	e2 = (T)it2.next();
        }
        return auxiliar;
    }
 
    @Override
    public String toString(){
    	Iterator it = this.iterator();
    	String Lista =" ";
    	while(it.hasNext()) {
    		Lista += it.next()+" ";
    	}
        return Lista; 
    }


}