drop table if exists anag_archive_files;
drop table if exists anag_file_extensions;

create table anag_file_extensions (
	extension_id serial,
	file_extension text unique not null,
	primary key (extension_id),
	check (file_extension != '')
);

create table anag_archive_files (
	file_id serial,
	file_name text unique not null,
	file_day integer,
	file_month integer, 
	file_year integer,
	file_prog_number integer not null,
	file_extension text not null,
	file_path text unique not null,
	file_hash text unique not null,
	primary key (file_id),
	foreign key (file_extension) references anag_file_extensions(file_extension),
	check (file_name != '')
);