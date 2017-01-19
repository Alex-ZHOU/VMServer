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
(usr_id,usr_account,nickname,record_times,average_db,max_db,min_db,record_minter) 
values (1,"133","133",0,0,0,0,0);
SELECT * FROM base_info where usr_id=1; 
UPDATE base_info set record_times=record_times+1,average_db=22,max_db=70,min_db=50,record_minter=99 WHERE usr_id=1;

-- TABLE advertising_column
insert into advertising_column (advertisement_id,image_id,title) values (1,1,"惠州曼哈顿");
insert into advertising_column (advertisement_id,image_id,title) values (2,10,"清吧");
insert into advertising_column (advertisement_id,image_id,title) values (3,20,"欢唱100");
insert into advertising_column (advertisement_id,image_id,title) values (4,5,"揭阳楼");
SELECT * FROM advertising_column; 

-- TABLE store_info
insert into store_info (advertisement_id,image_id,title,average_db,summary,latitude,longitude) values (1,5,"惠州曼哈顿",55,"惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次还不错，去过一次惠州xxx热舞酒店，还不错，去过一次",23.530347,116.38376);
SELECT * FROM store_info ;

-- TABLE store_record_db
insert into store_record_db (store_id,usr_id,year,month,day,reocrd_status)values(1,1,2017,1,2,1);
insert into store_record_db (store_id,usr_id,year,month,day,reocrd_status)values(1,2,2017,1,2,1);
SELECT * FROM store_record_db where store_id = 1 && reocrd_status=1;