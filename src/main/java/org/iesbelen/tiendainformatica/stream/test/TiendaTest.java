package org.iesbelen.tiendainformatica.stream.test;

import java.util.*;

import org.iesbelen.tiendainformatica.entity.Fabricante;
import org.iesbelen.tiendainformatica.dao.FabricanteDAOImpl;
import org.iesbelen.tiendainformatica.entity.Producto;
import org.iesbelen.tiendainformatica.dao.ProductoDAOImpl;
import org.junit.jupiter.api.Test;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TiendaTest {

	private FabricanteDAOImpl fabricantesDAOImpl;
	private ProductoDAOImpl productosDAOImpl;


	public TiendaTest(){
		Fabricante fabricante = null;
		fabricantesDAOImpl = new FabricanteDAOImpl();
		productosDAOImpl = new ProductoDAOImpl();
		try {
			fabricantesDAOImpl.beginTransaction();
			productosDAOImpl.beginTransaction();

			// Creación de fabricantes y productos
			// Asus
			fabricante = new Fabricante("Asus");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"Monitor 27 LED Full HD",199.25));
			productosDAOImpl.create(new Producto(fabricante,"Monitor 24 LED Full HD",119.99));

			// Lenovo
			fabricante = new Fabricante("Lenovo");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"Portátil IdeaPad 320",359.65));
			productosDAOImpl.create(new Producto(fabricante,"Portátil Yoga 520",452.79));

			// Hewlett-Packard
			fabricante = new Fabricante("Hewlett-Packard");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"Impresora HP Deskjet 3720",59.99));
			productosDAOImpl.create(new Producto(fabricante,"Impresora HP Laserjet Pro M26nw",111.86));

			// Samsung
			fabricante = new Fabricante("Samsung");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"Disco SSD 1 TB",72.99));

			// Seagate
			fabricante = new Fabricante("Seagate");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"Disco duro SATA3 1TB",38.49));

			// Crucial
			fabricante = new Fabricante("Crucial");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"GeForce GTX 1080 Xtreme",611.55));
			productosDAOImpl.create(new Producto(fabricante,"Memoria RAM DDR4 8GB",24.19));

			// Gigabyte
			fabricante = new Fabricante("Gigabyte");
			fabricantesDAOImpl.create(fabricante);
			productosDAOImpl.create(new Producto(fabricante,"GeForce GTX 1050Ti",319.19));

			// Huawei sin productos a insertar
			fabricante = new Fabricante("Huawei");
			fabricantesDAOImpl.create(fabricante);

			// Xiaomi sin productos a insertar
			fabricante = new Fabricante("Xiaomi");
			fabricantesDAOImpl.create(fabricante);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
			fabricantesDAOImpl.rollbackTransaction();
			throw e; // or display error message
		}
	}
	
	@Test
	void testSkeletonFrabricante() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();
		
			
			//TODO STREAMS
			

		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonProducto() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();		
						
			//TODO STREAMS

		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllFabricante() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();
			assertEquals(9,listFab.size());
		
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllProducto() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();		
			assertEquals(11,listProd.size());
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {

		try {
			productosDAOImpl.beginTransaction();
			
			List<Producto> listProd = productosDAOImpl.findAll();

			//stream con collect toList  List<String[]>
			List<String[]> lstNombrePrecio = listProd.stream()
					.map( p  -> new String[] {p.getNombre(), Double.toString(p.getPrecio())} )
					.collect(toList());

			//Representando con forEach
			lstNombrePrecio.forEach( s -> System.out.println("Nombre: " + s[0]+ ", Precio: " +s[1]) );
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {

		try {
			productosDAOImpl.beginTransaction();			
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> listProdPrecioDolar =listProd.stream()
					.map(p -> {
								p.setPrecio(p.getPrecio() * 0.9);
								return p;
						})
					.collect(toList());

			listProdPrecioDolar.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lstNombreUCPrecio = listProd.stream()
							.map( p -> new StringBuffer().append("Nombre: ")
														.append(p.getNombre().toUpperCase())
														.append(", Precio: ")
														.append(p.getPrecio())
														.toString()
							)
							.collect(toList());

			lstNombreUCPrecio.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> listNombreSiglas = listFab.stream()
					.map( f -> new StringBuffer()
									.append(f.getNombre())
									.append(", ")
									.append(f.getNombre().substring(0,2).toUpperCase())
									.toString()
					)
					.collect(toList());

			listNombreSiglas.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Integer> lstFabConProd = listFab.stream()
					.filter( f -> f.getProductos().size() > 0)
					.map(Fabricante::getIdFabricante)
					.collect(toList());

			lstFabConProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> lstFabConProd = listFab.stream()
					.map(Fabricante::getNombre)
					.sorted(reverseOrder())
					.collect(toList());

			lstFabConProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Lista los nombres de los productosDAOImpl ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> listProdSorted = listProd.stream()
					.sorted(comparing(Producto::getNombre)
						.thenComparing(Producto::getPrecio, reverseOrder())
					)
					.collect(toList());

			listProdSorted.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Fabricante> lstFab5 = listFab.stream()
					.limit(5)
					.collect(toList());

			lstFab5.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Fabricante> lstFab2 = listFab.stream()
					.skip(3)
					.limit(2)
					.collect(toList());

			lstFab2.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Optional<String> optStr = listProd.stream()
					.sorted(comparing(Producto::getPrecio))
					.limit(1)
					.map( p -> "Nombre: "+ p.getNombre() + ", Precio: " + p.getPrecio())
					.findAny();
					//.findFirst();
					//.reduce( (p1, p2) -> p1);

			optStr.ifPresent(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Optional<String> optStr = listProd.stream()
					.sorted(comparing(Producto::getPrecio).reversed())
					.limit(1)
					.map( p -> "Nombre: "+ p.getNombre() + ", Precio: " + p.getPrecio())
					.findAny();
				//.findFirst();
				//.reduce( (p1, p2) -> p1);

			optStr.ifPresent(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lstStr = listProd.stream()
					.filter( p -> p.getFabricante().getIdFabricante() == 2)
					.map( p -> p.getNombre() )
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lstStr = listProd.stream()
					.filter( p ->  p.getPrecio() <= 120d)
					.map( p -> p.getNombre() )
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> lsProd = listProd.stream()
					.filter( p ->  p.getPrecio() >= 400d)
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€.
	 */
	@Test
	void test15() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> lsProd = listProd.stream()
					.filter( p ->  p.getPrecio() >= 80d && p.getPrecio() <= 300d)
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> lsProd = listProd.stream()
					.filter( p ->  p.getPrecio() >= 200 && p.getFabricante().getIdFabricante() == 6)
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
	
		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Set<Integer> setI = new HashSet<>();
			setI.add(1);
			setI.add(3);
			setI.add(5);

			List<Producto> lsProd = listProd.stream()
					.filter( p ->  setI.contains(p.getFabricante().getIdFabricante()) )
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {

		try {
			productosDAOImpl.beginTransaction();

			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lsProd = listProd.stream()
					.map(p -> "Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio()*100 )
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	
	/**
	 * 19. Lista los nombres de los fabricante cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Fabricante> lstFab2 = listFab.stream()
					.filter(f -> f.getNombre().toLowerCase().startsWith("s"))
					.collect(toList());

			lstFab2.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> lsProd = listProd.stream()
					.filter( p -> p.getNombre().contains("Portátil"))
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lsProd = listProd.stream()
					.filter( p -> p.getNombre().contains("Monitor") && p.getPrecio() < 215)
					.map(p -> p.getNombre())
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	@Test
	void test22() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lsProd = listProd.stream()
					.filter( p -> p.getPrecio() >= 180)
					.sorted(comparing(Producto::getPrecio, reverseOrder()).thenComparing(Producto::getNombre))
					.map(p -> "Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio())
					.collect(toList());

			lsProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos.
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lstStr = listProd.stream()
					.sorted(comparing(p -> p.getFabricante().getNombre()))
					.map( p -> "Producto:" + p.getNombre() + ", Precio:" + p.getPrecio() + ", Fabricante: " + p.getFabricante().getNombre())
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<String> lstStr = listProd.stream()
					.sorted(comparing(Producto::getPrecio).reversed())
					.limit(1)
					.map( p -> "Producto:" + p.getNombre() + ", Precio:" + p.getPrecio() + ", Fabricante: " + p.getFabricante().getNombre())
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			List<Producto> lstProd = listProd.stream()
					.filter(p -> "Crucial".equals(p.getFabricante().getNombre()) && p.getPrecio() > 200 )
					.collect(toList());

			lstProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Set<String> fabs = Set.of("Asus", "Hewlett-Packard", "Seagate");

			List<Producto> lstProd = listProd.stream()
					.filter(p -> fabs.contains( p.getFabricante().getNombre()) )
					.collect(toList());

			lstProd.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Optional<Integer> maxLengthNombreProd = listProd.stream().filter(p -> p.getPrecio() > 180).map( p -> p.getNombre().length()).max(Integer::compare);
			Optional<Integer> maxLengthPrecioProd = listProd.stream().filter(p -> p.getPrecio() > 180).map( p -> Double.toString(p.getPrecio()).length()).max(Integer::compare);

			StringBuilder sbNombre = new StringBuilder();
			for (int i = 0; i < maxLengthNombreProd.get(); i++) {
				sbNombre.append(' ');
			}

			StringBuilder sbPrecio = new StringBuilder();
			for (int i = 0; i < maxLengthPrecioProd.get(); i++) {
				sbPrecio.append(' ');
			}

			List<String> lstStr =listProd.stream()
					.filter(p -> p.getPrecio() > 180)
					.sorted(comparing(Producto::getPrecio,reverseOrder()).thenComparing(Producto::getNombre))
					.map(p ->  p.getNombre() + sbNombre.substring(p.getNombre().length()) + "|" + p.getPrecio() + sbPrecio.substring(Double.toString(p.getPrecio()).length()) + "|" + p.getFabricante().getNombre())
					.collect(toList());

			System.out.println("Producto" + sbNombre.substring("Producto".length()) + " Precio" + sbPrecio.substring("Precio".length()) + " Fabricante");
			System.out.println(sbNombre.toString().replace(" ", "-") + "-" + sbPrecio.toString().replace(" ", "-") + "|Fabricante".replaceAll(".", "-"));

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos.
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados.
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> lstStr = listFab.stream()
					.map(f -> "Fabricante: " + f.getNombre() + "\n\n" + "\tProductos:\n"
							+ f.getProductos().stream()
								.map(p -> "\t" + p.getNombre()).collect(joining("\n")) + "\n")
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {

		try {
			fabricantesDAOImpl.beginTransaction();
	
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Fabricante> lstFabNoProds = listFab.stream()
					.filter(f -> f.getProductos().isEmpty())
					.collect(toList());

			lstFabNoProds.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Long num =listProd.stream()
					.count();

			System.out.println(num);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}

	
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Long numFabsConProds = listProd.stream()
					.map(p -> p.getFabricante().getIdFabricante())
					.distinct()
					.count();

			System.out.println(numFabsConProds);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
	

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Double mediaProds = listProd.stream()
					.collect(averagingDouble(Producto::getPrecio));

			System.out.println(mediaProds);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Double minPrecio = listProd.stream()
					.collect(minBy(comparing(Producto::getPrecio)))
					.get()
					.getPrecio();

			System.out.println(minPrecio);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Double suma = listProd.stream()
					.collect(summingDouble(Producto::getPrecio));

			System.out.println(suma);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Long num =listProd.stream()
					.filter(p -> "Asus".equals(p.getFabricante().getNombre()))
					.count();

			System.out.println(num);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 36. Calcula la media del precio de todos los productosdel fabricante Asus.
	 */
	@Test
	void test36() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Double mediaPrecioAsus =listProd.stream()
					.filter(p -> "Asus".equals(p.getFabricante().getNombre()))
					.collect(averagingDouble(Producto::getPrecio));

			System.out.println(mediaPrecioAsus);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial.
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {

		try {
			productosDAOImpl.beginTransaction();
		
			List<Producto> listProd = productosDAOImpl.findAll();

			Double[] aD =listProd.stream()
					.filter(p -> "Crucial".equals(p.getFabricante().getNombre()))
					.map(p -> new Double[] {p.getPrecio(), p.getPrecio(), null, p.getPrecio()})
					//.peek(p -> {for ( Double d : p) System.out.println(d);})
					.reduce(new Double[] {null, null, 0.0d, 0.0d},(ant, act) -> {

						if (ant[0] != null) act[0] = Math.max(ant[0],act[0]);
						if (ant[1] != null) act[1] = Math.min(ant[1],act[1]);

						act[2] = ant[2] +1;
						act[3] = ant[3] + act[3];

						return act;
					});

			Arrays.stream(aD).forEach(System.out::println);
		}
		catch (RuntimeException e) {
			productosDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes.
	 * El listado también debe incluir los fabricantes que no tienen ningún producto. 
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene.
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:
	 
     Fabricante     #Productos
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
           Asus              2
         Lenovo              2
Hewlett-Packard              2
        Samsung              1
        Seagate              1
        Crucial              2
       Gigabyte              1
         Huawei              0
         Xiaomi              0

	 */
	@Test
	void test38() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			Integer longitudMax =listFab.stream()
					.map( f -> f.getNombre().length())
					.max(Integer::compare)
					.get();
			List<String> lstStr = listFab.stream()
					.map( f -> String.format("%1$" + longitudMax + "s",f.getNombre()) + String.format("%1$" + longitudMax + "s",f.getProductos().size()))
					.collect(toList());

			System.out.println(String.format("%1$" + longitudMax + "s","Fabricante")
					+ String.format("%1$" + longitudMax + "s","#Productos"));
			System.out.println(String.format("%1$" + longitudMax + "s","Fabricante").replaceAll(".", "-*")
					+ String.format("%1$" + longitudMax + "s","#Productos").replaceAll(".", "-*"));
			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productosDAOImpl.
	 */
	@Test
	void test39() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> lstStr = listFab.stream().map( f -> {
				Double[] aD = f.getProductos().stream()
						.map( p -> new Double[] {p.getPrecio(), p.getPrecio(),null, p.getPrecio()})
						.reduce(new Double[] {null, null, 0.0d, 0.0d}, (ant, act) -> {

							if (ant[0] != null) act[0] = Math.max(ant[0],act[0]);
							if (ant[1] != null) act[1] = Math.min(ant[1],act[1]);

							act[2] = ant[2] +1;
							act[3] = ant[3] + act[3];

							return act;
						});
				return "Fabricante: " + f.getNombre() + " | Precio Max. = " + aD[0] + ", Precio Mín. = " + aD[1] + ", Precio medio = " + (aD[3] / aD[2]);
			} ).collect(toList());
			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€.
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> lstStr = listFab.stream().map( f -> {
						Double[] aD = f.getProductos().stream()
								.map( p -> new Double[] {p.getPrecio(), p.getPrecio(),null, p.getPrecio()})
								.reduce(new Double[] {null, null, 0.0d, 0.0d}, (ant, act) -> {

									if (ant[0] != null) act[0] = Math.max(ant[0],act[0]);
									if (ant[1] != null) act[1] = Math.min(ant[1],act[1]);

									act[2] = ant[2] +1;
									act[3] = ant[3] + act[3];

									return act;
								});
						return new Object[] { f.getNombre(), aD[0], aD[1], (aD[3] / aD[2])};
					} )
					.filter( o -> ((Double)o[3]) > 200)
					.map( o -> "Fabricante: " + o[0] + " Precio Max. = " + o[1] + " Precio Min. = " + o[2] + " Precio medio = " + o[3] )
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<String> lstStr = listFab.stream()
					.filter(f -> f.getProductos().size() >= 2)
					.map(Fabricante::getNombre)
					.collect(toList());

			lstStr.forEach(System.out::println);
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €.
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Object[]> lstStr = listFab.stream()
					.map(f -> {
						return new Object[] {f.getNombre(), f.getProductos().stream().filter(p -> p.getPrecio() >= 200).count()};
					})
					.sorted(comparing(o -> (Long)o[1], reverseOrder()))
					.collect(toList());

			lstStr.forEach( ao -> System.out.println("Fabricante: " + ao[0] + ", Conteo productos: " + ao[1]));
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Object[]> lstStr = listFab.stream().map(f -> {
						return new Object[] {f.getNombre(), f.getProductos().stream().filter(p -> p.getPrecio() >= 200).count()};
					}).sorted(comparing(o -> (Long)o[1], reverseOrder()))
					.collect(toList());

			lstStr.forEach( ao -> System.out.println("Fabricante: " + ao[0] + ", Conteo productos: " + ao[1]));
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Object[]> lstStr = listFab.stream()
					.map(f -> {
						return new Object[] {f.getNombre(), f.getProductos().stream()
																.collect(summingDouble(Producto::getPrecio)) };
					})
					//.peek( o -> System.out.println("Fabricante: " + o[0] + ", Suma productos: " + o[1]))
					.filter( o -> (Double)o[1] > 100)
					.sorted(comparing(o -> (Double)o[1]))
					.collect(toList());

			lstStr.forEach( ao -> System.out.println("Fabricante: " + ao[0] + ", Suma productos: " + ao[1]));
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. 
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. 
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {

		try {
			fabricantesDAOImpl.beginTransaction();
				
			List<Fabricante> listFab = fabricantesDAOImpl.findAll();

			List<Object[]> lstStr = listFab.stream().map(f -> {
						return new Object[] {f.getNombre(), f.getProductos().stream()
																			.collect(maxBy(comparing(Producto::getPrecio))) };
					})
					//.peek( o -> System.out.println("Fabricante: " + o[0] + ", Producto: " + o[1]))
					.sorted(comparing(o -> (String)o[0],naturalOrder()))
					.collect(toList());

			lstStr.forEach( ao -> System.out.println( "Producto: " + ( ((Optional<Producto>)ao[1]).isPresent() ? ((Optional<Producto>)ao[1]).get().getNombre() : "Sin producto" )+ ", Precio: " + ( ((Optional<Producto>)ao[1]).isPresent() ? ((Optional<Producto>)ao[1]).get().getPrecio() : "Sin producto" ) + ", Fabricante: " + ao[0]));
		}
		catch (RuntimeException e) {
			fabricantesDAOImpl.rollbackTransaction();
		    throw e; // or display error message
		}
	}
}

