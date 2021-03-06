-- VoiceMonitor mysql数据库的增删查改

-- TABLE usr_info
SELECT * FROM usr_info ;
SELECT * FROM usr_info where usr_account="13aa3";
insert into usr_info 
(usr_account,usr_password) 
values ("133","1224");

-- TABLE record_db
SELECT * FROM record_db where usr_id=1;
SELECT * FROM record_db where usr_id=1  order by record_db_id desc;

-- TABLE base_info
insert into base_info 
(usr_id,usr_account,nickname,record_times,average_db,max_db,min_db,record_minter,head_protrait) 
values (1,"133","Alex",0,0,0,0,0,1);
insert into base_info 
(usr_id,usr_account,nickname,record_times,average_db,max_db,min_db,record_minter,head_protrait) 
values (2,"123","cmk",0,0,0,0,0,2);
insert into base_info 
(usr_id,usr_account,nickname,record_times,average_db,max_db,min_db,record_minter,head_protrait) 
values (3,"124","crj",0,0,0,0,0,3);
SELECT * FROM base_info;
SELECT * FROM base_info where usr_id=1; 
UPDATE base_info set record_times=record_times+1,average_db=22,max_db=70,min_db=50,record_minter=99 WHERE usr_id=1;

-- TABLE advertising_column
insert into advertising_column (advertisement_id,image_id,title) values (1,1,"惠州曼哈顿");
insert into advertising_column (advertisement_id,image_id,title) values (2,10,"清吧");
insert into advertising_column (advertisement_id,image_id,title) values (3,20,"欢唱100");
insert into advertising_column (advertisement_id,image_id,title) values (4,5,"揭阳楼");
SELECT * FROM advertising_column; 

-- TABLE store_info
insert into store_info (advertisement_id,image_id,title,average_db,summary,latitude,longitude,type) values (1,5,"惠州曼哈顿",55,"惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次",23.530347,116.38376,"bar");
SELECT * FROM store_info ;
SELECT * FROM store_info where type="ktv";

-- TABLE store_record_db
insert into store_record_db (store_id,usr_id,year,month,day,reocrd_status)values(1,1,2017,1,2,1);
insert into store_record_db (store_id,usr_id,year,month,day,reocrd_status)values(1,2,2017,1,2,1);
SELECT * FROM store_record_db where store_id = 1 && reocrd_status=1;
SELECT * FROM store_record_db where store_id = 1 ;

-- TABLE image_id_to_address
insert into image_id_to_address (address) values ("/Image/head_portrait_1.jpg");
insert into image_id_to_address (address) values ("/Image/head_portrait_2.jpg");
insert into image_id_to_address (address) values ("/Image/head_portrait_3.jpg");
SELECT * FROM image_id_to_address where image_id = 1;
UPDATE `VoiceMonitor`.`image_id_to_address` SET `address`='/Image/head_portrait_1.jpg' WHERE `image_id`='1';


SELECT * from store_record_db where reocrd_status = 1;