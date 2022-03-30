create table stock_purchase (
  purchase_id identity primary key,
  symbol varchar(10),
  price_per_share double,
  shares double,
  purchase_date timestamp
);