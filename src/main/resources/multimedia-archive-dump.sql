drop table if exists anag_file_extensions cascade;
drop table if exists anag_archives cascade;
drop table if exists anag_archive_files;
drop table if exists ass_archive_extension;

create table anag_file_extensions (
	extension_id serial,
	file_extension text unique not null,
	primary key (extension_id),
	check (file_extension != '')
);

create table anag_archives (
	archive_id serial,
	archive_name text unique not null,
	archive_path text unique not null,
	primary key (archive_id),
	check (archive_name != ''),
	check (archive_path != '')
);

create table ass_archive_extension(
	ass_id serial,
	archive_id integer not null,
	extension_id integer not null,
	primary key (ass_id),
	foreign key (archive_id) references anag_archives(archive_id),
	foreign key (extension_id) references anag_file_extensions(extension_id)
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
	archive_id integer,
	primary key (file_id),
	foreign key (file_extension) references anag_file_extensions(file_extension),
	foreign key (archive_id) references anag_archives(archive_id),
	check (file_name != '')
);

insert into anag_file_extensions(file_extension)
	values ('jpg'), ('JPG'), ('jpeg'), ('png'), ('gif'), ('mp4'), ('opus'), ('mpeg'), ('mp3');
	
insert into anag_archives(archive_name, archive_path)
	values ('Prova', 'Path prova');