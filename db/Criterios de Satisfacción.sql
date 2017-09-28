select * from customer;

-- Promedio 1000 pedidos por mes (no debería ser exacto, oscilar entre +-300 pedidos
 
select sum(data.orders), max(data.orders), min(data.orders),trunc(avg(data.orders)) as promedio
from (select date_trunc('month',o.date_of_entry) as month,count(*) as orders 
	  from order_ as o
      group by date_trunc('month',o.date_of_entry)
      ) as data


-- Al menos 100 clientes donde para cada uno exista entre 1 y 3 domicilios de entrega.
select address as address_count,count(customer) as customer
from(
	   	 select c.id as customer , count(a.id) as address 
	     from customer as c join address as a on (a.customer_id = c.id)
		 group by c.id
		 )as data
group by data.address 
order by data.address
;

-- Cada pedido debe tener con una distribución uniforme de artículos entre 2 y 10 artículos por pedido
select max(articles) as max_article, min(articles) as min_article, avg(articles) as avg_article
from(
		select o.id , count(ao.id) as articles
		from order_ as o join article_order as ao on (ao.order_id = o.id)
		group by o.id
		) as data
;

-- Cantidad de pedidos que tienen N articulos
select articles , count(order_) as count_order
from(
		select o.id as order_, count(ao.id) as articles
		from order_ as o join article_order as ao on (ao.order_id = o.id)
		group by o.id
		) as data
group by data.articles
order by data.articles
;

 -- La selección de artículos debe contar con una distribución uniforme sobre todos los artículos de la base
-- Pendiente!!

-- Generar entre 1 y 100 articulos por articulo_pedido  
select max(quantity) as max_articles, min(quantity) as min_articles, avg(quantity) as avg_articles 
from article_order;

--  La generación de pedidos debe tener una distribución uniforme por día

-- Pendiente!!

-- múltiples domicilios, múltiples pedidos para el mismo cliente mismo domicilio en el mismo día. Artículos sin stock.

-- 1 pedido                      Cantidad
 
-- 2 pedidos mismo domicilio     Cantidad

-- 2 pedidos distinto domicilio  Cantidad

-- no usado 
select p.date_of_entry,p.address_id,count(*) as cantPorDia
from order_ as p 
group by p.date_of_entry,p.address_id
having count(*)>1
order by p.date_of_entry
;
select * from order_ as o
where o.customer_id = 7 
and o.date_of_entry = '2016-01-01';



select p.date_of_entry,p.customer_id,count(*) as cantPorDia
from order_ as p
where exists (select * 
			  from order_ as p1 	
			  where p.date_of_entry = p1.date_of_entry  
			  and p.customer_id= p1.customer_id 
			  and p.address_id != p1.address_id
			  )
group by p.date_of_entry,p.customer_id
having count(*)>1
order by p.date_of_entry
;



-- diferencia de dias entre pedido y remito
select count(*),t.maximo from (
								select (s.release_date - o.date_of_entry) as maximo
								from shipment as s
								join article_order as ra on (s.id = ra.shipment_id)
								join order_ as o on (o.id = ra.order_id)
								)as t 
								group by t.maximo
								order by 1 desc;


-- Muestra que se generan mas de 2 pedidos para el mismo dia
SELECT o.date_of_entry,o.id as order_id,a.customer_id as customer_id,a.id as address_id 
FROM order_ o join address as a on (a.id = o.address_id)
WHERE (a.customer_id,o.date_of_entry) IN (
-- Busca todas las tuplas, para el mismo cliente, misma fecha , donde la cantidad 
-- de domicilios de entrega se mayor a 2
										select a.customer_id,o.date_of_entry
										from order_ as o join address as a on (a.id = o.address_id)
										group by o.date_of_entry,a.customer_id
										having count(o.address_id) > 1
										order by a.customer_id , o.date_of_entry
										)
ORDER BY o.date_of_entry;

								
								
-- Simulación de generación de los remitos. Se debe generar un script que procese los pedidos generados en un día
-- , generando los remitos respectivos. No deben quedar los remitos generados todos el mismo día,
--  garantizando que coincida la fecha de pedido con la de remito o a lo sumo que difiera un día.
-- Pendiente!!


-- Simulación de recepción de mercadería. La misma debe realizarse con una distribución uniforme entre el día de generación del remito y hasta los 3 días subsiguientes.
-- Pendiente!!

-- Simulación del proceso de reposición de mercadería. Luego de la generación de remitos se deben recorrer todos 
-- aquellos los artículos que tengan menos de 100 unidades, generando una reposición aleatoria entre 100 y 200 unidades 
-- a reponer (sumar a las existentes).
-- Pendiente!!

-- Simular el proceso de facturación de los remitos recepcionados, manteniendo las mismas condiciones de generación entre la
-- fecha de recepción y a lo sumo 3 días posteriores.
-- Pendiente!!

select * from article_order;
select * from article_order_provider;
select * from shipment_provider;

select a.id,a.stock,a.name from article as a
where a.id= 41685;

select ao.id,ao.price,a.id as article , a.stock  from article_order_provider as ao, article as a 
where (ao.article_id = a.id)
and ao.shipmentprovider_id = 1550;


delete from article_order_provider;
delete from shipment_provider;



