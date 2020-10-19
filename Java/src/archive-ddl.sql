create table if not exists files 
	(file_id integer primary key, file_name text unique, day integer, month integer, 
	year integer, prog_number integer, eof text, path text unique, hash text unique)