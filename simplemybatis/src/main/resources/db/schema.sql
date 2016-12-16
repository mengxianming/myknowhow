CREATE TABLE renter( 
  id INT (11) NOT NULL PRIMARY KEY
  , name VARCHAR (32) NOT NULL
  , phone VARCHAR (32)
  , age int
); 

CREATE TABLE room( 
  id INT (11) NOT NULL PRIMARY KEY
  , name VARCHAR (32) NOT NULL
  , area decimal(5,2)
  , createtime timestamp
); 