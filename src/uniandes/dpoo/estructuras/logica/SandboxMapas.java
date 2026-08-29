
package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista( )
    {
    	List<String> listaValores = new ArrayList<>(mapaCadenas.values());
    	listaValores.sort(Comparator.naturalOrder());
    	return listaValores; 
    }

    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	List<String> llavesInvertidas = new ArrayList<>(mapaCadenas.keySet());
    	llavesInvertidas.sort(Comparator.reverseOrder());
        return llavesInvertidas;
    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if(mapaCadenas.size() >0) {
    		String menor = null;
    		for(String c : mapaCadenas.keySet()) {
    			if (menor == null || c.compareTo(menor) < 0) {
    				menor = c;
    			}
    		}
    		return menor;
    	}
        return null;
    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
        if (mapaCadenas.size() > 0) {
        	String mayor = null;
        	for (String c : mapaCadenas.values()) {
        		if (mayor == null || c.compareTo(mayor) > 0) {
        			mayor = c;
        		}
        	}
        	return mayor;
        	
        }
        return null;
    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {   	
    	List<String> listaLlavesMayuscula = new ArrayList<>(mapaCadenas.keySet());
    	for (int i=0; i<listaLlavesMayuscula.size(); i++) {
    		listaLlavesMayuscula.set(i, listaLlavesMayuscula.get(i).toUpperCase());
    	}
    	
    	return listaLlavesMayuscula;
        
    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	List<String> diferentes = new ArrayList<>();
    	for (String value: mapaCadenas.values()) {
    		if (!diferentes.contains(value)) {
    			diferentes.add(value);
    		}
    	}
    	return diferentes.size();
    	
    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String reverse = "";
    	for(int i = cadena.length() -1 ; i >= 0; i--) {
    		reverse += cadena.charAt(i);
    	}
    	mapaCadenas.put(reverse,cadena);
    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);
    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	String llave = null;
    	for(Entry<String, String> entrada : mapaCadenas.entrySet()) {
    		if(entrada.getValue().equals(valor)) {
    			llave = entrada.getKey();
    		}
    	}
    	
    	if (llave != null) {
    		eliminarCadenaConLLave(llave);
    	}
    	
    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear();
    	for(int i=0; i<objetos.size(); i++) {
    		String cadena = objetos.get(i).toString();
    		agregarCadena(cadena);
    	}
    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> mapaNuevo = new HashMap<String, String>();
    	for(Entry<String, String> entrada : mapaCadenas.entrySet()) {
    		String llave = entrada.getKey().toUpperCase();
    		String valor = entrada.getValue();
    		mapaNuevo.put(llave, valor);
    	}
    	mapaCadenas = mapaNuevo;
    	
    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	List<String> cadenaValores = getValoresComoLista();
        for(int i=0; i<otroArreglo.length; i++) {
        	boolean existe = false;
        	for (int j=0; j<cadenaValores.size(); j++) {
        		if (cadenaValores.get(j).equals(otroArreglo[i])) {
        			existe = true;
        		}
        	} if (!existe) {
        		return false;
        	}
        }
        
    	return true;
    }

}
