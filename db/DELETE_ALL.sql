DELETE FROM  users;

DELETE FROM  address;

DELETE FROM  customer;
DELETE FROM  article;

DELETE FROM  city;
DELETE FROM  province;
DELETE FROM  country;

select * from users;
select * from address;
select * from customer;
select * from city;
select * from province;
select * from country;

select * from shipment;

delete from article_order;
delete from order_;
delete from shipment;

select count(*) from order_;

select count(*) from article_order;



--Todos los Pedidos con fecha de entrada , address_id , customer_id , customer_name 
select o.id as order_id,o.date_of_entry, a.id as address_id, c.id as customer_id, c.name
from order_ as o ,address as a,customer as c
where o.address_id = a.id and
a.customer_id = c.id
order by o.id;

-- Cantidad de Pedidos generados por dia
select o.date_of_entry , count(*) as orders
from order_ as o
group by o.date_of_entry
order by o.date_of_entry desc;


-- SELECT EXTRACT(YEAR FROM fu) FROM mydate;
-- Generar 12000 pedidos por año 
select count(*)  
from order_ as o
where (SELECT EXTRACT(YEAR FROM o.date_of_entry)) = 2016


-- promedio de pedidos generados por dia
select max(cc),min(cc),avg(cc)
from
(select count(*) as cc, o.date_of_entry
from order_ as o
group by o.date_of_entry
order by o.date_of_entry) as c;

-- promedio de pedidos generados por mes
select d,max(cc),min(cc),avg(cc)
from
	(select count(*) as cc, date_trunc('month',o.date_of_entry) as d
	 from order_ as o
group by date_trunc('month',o.date_of_entry)
) as c
group by c.d;

-- Pedidos con su cantidad de articulos
select o.id as order_id ,o.date_of_entry, count(*) as article_orders
from article_order as ao, order_ as o
where ao.order_id = o.id
group by o.id
order by o.id
;

-- Pedidos que se hayan realizado para el mismo cliente, el mismo dia, 
-- donde la cantidad de pedidos para el dia se mayor a 1  
select count(*),customer_id,date_of_entry from (
												select count(*),	a.customer_id,	o.date_of_entry
												from order_ as o join address as a 
												on a.id = o.address_id
												group by o.address_id , o.date_of_entry, a.customer_id
												-- having count(*) >1
												order by a.customer_id,o.date_of_entry
												) as c
group by c.date_of_entry,c.customer_id
-- having count(*) =3
;

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


-- info articulos pedidos
select ao.id , ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id ,ao.receipt_id
from article_order as ao join order_ as o
on ao.order_id = o.id
order by ao.shipment_id
;


-- verificar stock
select * from article_order;

select * from article as a
where a.id = 46223;

select * from article_order as a
where a.shipment_id = null;

-- todos los remitos 
select * from shipment;

-- remitos la misma fecha de entrega y fecha de armado
select * from shipment as s
where s.deliver_date = s.release_date
;

-- remitos con diferencias entre fecha de entrega y fecha de armado  
select * from shipment as s
where s.deliver_date != s.release_date
order by s.release_date
;

-- remitos erroneos fecha de entrega antes de fecha de armado  
select * from shipment as s
where s.deliver_date < s.release_date
;

select * from article_order as a 
where a.shipment_id = null;

select * from receipt;

-- full
select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id, r.delivery_date as r_delivery_date,r.payment_date
from article_order as ao , order_ as o , shipment as s, receipt as r
where ao.order_id = o.id and ao.shipment_id= s.id and ao.receipt_id =r.id
order by ao.order_id;



select count(*) from shipment;

-- order shipment
select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id
from article_order as ao , order_ as o , shipment as s
where ao.order_id = o.id 
and ao.shipment_id= s.id
order by ao.order_id;

-- probar que para el mismo domicilio se genera un solo remito
-- pendiente
select count(*) from order_;

-- diferencia de dias entre pedido y remito
select count(*),t.maximo from (
								select (s.release_date - o.date_of_entry) as maximo
								from shipment as s
								join article_order as ra on (s.id = ra.shipment_id)
								join order_ as o on (o.id = ra.order_id)
								)as t 
								group by t.maximo
								order by 1 desc;

-- ERROR order -> date of entry    <   shipment -> release date
select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id
from article_order as ao , order_ as o , shipment as s
where ao.order_id = o.id 
and ao.shipment_id= s.id
and o.date_of_entry > s.release_date
order by o.date_of_entry;

-- error 
select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id
from article_order as ao , order_ as o , shipment as s
where ao.order_id = o.id 
and ao.shipment_id= s.id
and s.release_date > s.deliver_date
order by o.date_of_entry;

select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id, r.delivery_date as r_delivery_date,r.payment_date
from article_order as ao , order_ as o , shipment as s, receipt as r
where ao.order_id = o.id 
and ao.shipment_id= s.id
and ao.receipt_id = r.id
and s.deliver_date > r.delivery_date
order by o.date_of_entry;


select ao.id as ao_id, ao.article_id , ao.order_id ,o.address_id,o.date_of_entry, ao.shipment_id,s.release_date,s.deliver_date as s_delivery_date,ao.receipt_id, r.delivery_date as r_delivery_date,r.payment_date
from article_order as ao , order_ as o , shipment as s, receipt as r
where ao.order_id = o.id 
and ao.shipment_id= s.id
and ao.receipt_id = r.id
and (o.date_of_entry > s.release_date
	OR  s.release_date > s.deliver_date
	OR  s.deliver_date > r.delivery_date
	OR	r.delivery_date > r.payment_date )
order by o.date_of_entry;


--select *
-- articulos pedidos sin remito ..
select ao.id,ao.quantity, a.id as article_id,a.price as price,a.stock, o.id as order_id,o.date_of_entry
from article_order as ao, article as a,order_ as o
where ao.article_id = a.id 
and ao.order_id = o.id
and ao.shipment_id is null;

-- huerfanos q no tengan remito...

select * 
from article_order as ao , order_ as o
where ao.order_id = o.id
and o.date_of_entry = '2016-01-15'
;
select count(*) from order_;

select * from article_order;

select a.id,a.stock from article as a
where stock < 100;


select * from provider;

select * from shipment_provider;

select * from article_order_provider;


delete from article_order;
delete from order_;
delete from shipment;
delete from receipt;
delete from article_order_provider;
delete from shipment_provider;