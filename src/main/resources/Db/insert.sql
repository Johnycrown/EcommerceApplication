set foreign_key_checks = 0;
truncate table product;
truncate table item;
truncate table cart;
truncate table cart_items_list;
truncate  table app_user;



insert into product(id, name, price, quantity)
values (12, 'Luxury Mop', 2430, 3),
(13, 'Macbook Air', 18320, 4),
(14, 'laptop', 183220, 6),
(15, 'Mercede benz', 18353320, 9),
(16, 'Highlander', 1878320, 8);

insert into item(id, product_id, quantity_added)
values (510,14,2),
       (511,15,3),
       (512,12,1);
insert into cart(id, total_price)
values (345, 0.00),
       (366, 0.00),
       (355, 0.00);
insert into cart_items_list(cart_id, items_list_id)
values (345,510),
  (366,511),
  (345, 513);

insert into app_user(id, first_name, last_name, email, my_cart_id)
values(5010, 'John', 'badmus', 'john@gmail.com',345 ),
 (5011, 'chris', 'Tuck', 'chris@gmail.com',366 ),
(5012, 'John', 'badmus2', 'john23@gmail.com',355 );
set foreign_key_checks = 1
