# Laboratorio de Programación y Lenguaje

# TP2-03-stats

## Problema

- A partir del ejercicio 2.01, se solicita realizar extensiones y adaptaciones, manteniendo los mismos criterios de satisfacción, para realizar una simulación de datos dichos procesos.

## Objetivo de la Solución 

- Contar con un tablero de comando para la gestión de información del software "lo llevamos a tu casa"

## Problemas surgidos


Problemas relacionados con el proyecto TP2-03-stats:  
    
    - El proyecto debe desplegarce en un servidor de aplicaciones java, en este caso usando tomcat7, este debio
      ser instalado en el S.O y no desde el IDE de desarrollo.
      
    - La persistencia se realiza en la base de datos "PostgreSQL", esta fue instalada y  configurada, sin problemas.
     		
	- El proyecto por recomendaciones de la catedra, debe ser desarrollado en eclipse EE, por lo tanto debe
	  ser configurado el eclipse y el tomcat7 ya instalado, en Ubuntu esto resulta algo complicado, debido a que no
	  fue facil, encontrar la instancia de tomcat7 "CATALINA_HOME", que se encuentra en /usr/share/tomcat7.
	  
	- Tambien fue necesario crear accesos directos de las configuraciones que se encuentran en CATALINA_BASE
	  situado en /var/lib/tomcat7.
	  
	- Luego de configurar el Tomcat7 para que funcione adecuadamente con el eclipse, debio configurarce el proyecto,
	  para ello fue necesario agregar depencias en el POM, entre ellas:
	  
				- Dependecias de Spring.
				- Dependecias de Hibernate.  	 
    			- Dependencias de JUnit.
    			- Dependecias de PostgreSQL
    			
    - El proyecto pudo ser desplegado en el servidor, pero no se realizaban cambios en la BD, esto se debia a que
      la conexión a la BD no estaba configurada, entonces debio configurarce el archivo "aplication.properties".

	- Para poder mantener la misma session desde dos Daos, se debio crear una variable LocalSessionFactoryBean en el archivo RepositoryConfig, e intentar implementar un singleton para los daos obtengan la referencia de la misma Session, en algo momento se deber refactorizar dicho singleton.
	- Para solucionar el problemas de los lazy, se agrego una propiedad a HibernateProperties, esta habilita el lazy en las entidades con la siguiente linea:
	
		- properties.put("hibernate.enable_lazy_load_no_trans", true);
	
	- Pequeño detalle!... al intentar crear la entidad Order, esta no se creaba en la base de datos, esto fue debido a que order es una palabra reservada.      
    
	- Se decio definir la busqueda de la lista de Direcciones de un cliente al tipo (fetch = FetchType.EAGER), debido a que (fetch = FetchType.LAZY) no funciona adecuadamente.
	
	- Tambien fue necesario eliminar las relaciones bidireccionales, estas hacian un sin fin de referencias, al momento de usar el RestService.
	- Al momento de generar pedidos se decidio implementar bulkSave, debido a que si se usaba hibernateTemplate.save el proceso de generacion de pedidos para un año tardaba 40 min aprox.
	- En un momento el proceso de simulacion generaba para una fecha especifica, pedidos de distintas fechas, esto sucedia 
	debido a que el Chrome AVECES hace dos llamas al servicio de simulación, esto fue solucionado agregando Postman( extencion de google chrome que permite realizar peticiones Rest) a Chorme.   
    
    