-- search product
select b.building_id, building_name, latitude, longitude
from building as b, restroom as r
where b.building_id = r.building_id
and r.product_status = 1;

-- get building information
select building_name, latitude, longitude
from building
where building_id = 1; -- the building id

select floor_name, room_num
from restroom
where building_id = 1  -- the building id
and r.product_status = 1
order by floor_name;

-- report missing
update restroom
set product_status = 0
where restroom_id = 1;  -- the restroom id you want

-- report refill
update restroom
set product_status = 0
where restroom_id = 0;  -- the restroom id you want

-- generate qr code
select restroom_id, building_num, building_name, floor_name, room_num
from building as b, restroom as r
where b.building_id = r.building_id;

select restroom_id, building_num, building_name, floor_name, room_num
from building as b, restroom as r
where b.building_id = r.building_id
and building_num = 1
and building_name = "a"
and floor_name = '1'
and room_num = 1;
