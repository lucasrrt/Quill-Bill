dropdb quillbill
createdb quillbill

psql quillbill -f schema.pgsql
psql quillbill -f seed.pgsql
