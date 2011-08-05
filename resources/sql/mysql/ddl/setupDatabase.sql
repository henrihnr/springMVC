drop database if exists @jdbc.dbname@;

create database @jdbc.dbname@;

use @jdbc.dbname@;

grant all on @jdbc.dbname@.* to @jdbc.username@@@jdbc.host@ identified by '@jdbc.password@';