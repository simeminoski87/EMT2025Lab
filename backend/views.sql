create or replace view books_per_country as
select c.id        as country_id,
       count(b.id) as num_books
from country c
         left join
     product b on b.category_id = c.id
group by c.id;

create materialized view products_per_author as
select a.id        as author_id,
       count(b.id) as num_books
from manufacturers a
         left join
     product b on b.manufacturer_id = a.id
group by a.id;
