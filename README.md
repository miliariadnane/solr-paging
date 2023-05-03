Sample Spring Boot project demonstrating basic and advanced paging features of Apache Solr.

Instructions:

Step 1.Download Apache Solr latest version(currently 9.2.1) from http://lucene.apache.org/solr/mirrors-solr-latest-redir.html
Refer to Solr quick start doc: http://lucene.apache.org/solr/quickstart.html

ls solr*
tar -xvf solr-9.2.1.tgz
cd solr-9.2.1
bin/solr start -e cloud -noprompt
bin/solr create -c books
open url http://localhost:8983/solr/#/books/ in browser


Step 2.Create books schema
Run SchemaCreator class to create fields

Step 3.Load sample data
Run DocumentLoader class to load sample data books.csv resource

Step 4.Run SpringBootApplication class to start web project

Step 5.Open url http://localhost:8080/solr-sb-bookstore/basic-paging?page_size=10&page_num=1&sort_col=book_id&sort_dir=1

Step 6.Open url http://localhost:8080/solr-sb-bookstore/advanced-paging?page_size=10&sort_col=book_id&sort_dir=1


Basic paging parameters:
page_size number of rows per page, default 10
page_num page number to get, default 1
sort_col sorting column, default id
sort_dir sorting direction, 1 asc, -1 desc

GET http://localhost:8080/solr-sb-bookstore/basic-paging?page_size=10&page_num=1&sort_col=book_id&sort_dir=1


Advanced paging parameters:
page_size number of rows per page, default 10
sort_col sorting column, default id
sort_dir sorting direction, 1 asc, -1 desc
cursor_mark continue from this point, default *

GET 1st page http://localhost:8080/solr-sb-bookstore/advanced-paging?page_size=10&sort_col=book_id&sort_dir=1

Use cursor_mark from first response before retrieving next page
GET next page: http://localhost:8080/solr-sb-bookstore/advanced-paging?page_size=10&sort_col=book_id&sort_dir=1&cursor_mark=AoIhMz8FMzhkMzE4ODUtODNmNS00MzY4LWI4OTQtMTNjYzU4NTQ3MDVh

Response body:
{
  "paging_data": {
    "page_size": 10,
    "page_number": 0,
    "sort_column": "book_id",
    "sort_direction": 1,
    "row_count": 16,
    "page_count": 2,
    "cursor_mark": "AoIhMz8FMzhkMzE4ODUtODNmNS00MzY4LWI4OTQtMTNjYzU4NTQ3MDVh"
  },
  "books": [
    {
      "id": "1",
      "title": "AWS Lambda in Action",
      "author": "Danilo Poccia",
      "category": "cloud",
      "publisher": "Manning",
      "publish_year": "2017"
    },
    {
      "id": "10",
      "title": "The Algorithm Design Manual Second Edition",
      "author": "Steven Skiena",
      "category": "algorithms",
      "publisher": "Springer",
      "publish_year": "2008"
    },
    {
      "id": "11",
      "title": "Hadoop Security",
      "author": "Ben Spivey",
      "category": "big data",
      "publisher": "OReilly",
      "publish_year": "2015"
    },
    {
      "id": "12",
      "title": "Programming Hive",
      "author": "Jason Rutberglen",
      "category": "big data",
      "publisher": "OReilly",
      "publish_year": "2012"
    },
    {
      "id": "13",
      "title": "Practical Hive",
      "author": "Scott Shaw",
      "category": "big data",
      "publisher": "Apress",
      "publish_year": "2016"
    },
    {
      "id": "14",
      "title": "Storm Applied",
      "author": "Sean Allen",
      "category": "big data",
      "publisher": "Manning",
      "publish_year": "2015"
    },
    {
      "id": "15",
      "title": "Data Algorithms",
      "author": "Mahmoud Parsian",
      "category": "data science",
      "publisher": "OReilly",
      "publish_year": "2015"
    },
    {
      "id": "16",
      "title": "The Kimball Group Reader:Relentlessly Practical Tools for Data warehousing and Business Intelligence 2nd edition",
      "author": "Ralph Kimball",
      "category": "datawarehouse",
      "publisher": "Wiley",
      "publish_year": "2015"
    },
    {
      "id": "2",
      "title": "Data Science from Scratch",
      "author": "Joel Grus",
      "category": "data science",
      "publisher": "OReilly",
      "publish_year": "2015"
    },
    {
      "id": "3",
      "title": "Algorithms in a Nutshell 2nd edition",
      "author": "George T.Heineman",
      "category": "programming",
      "publisher": "OReilly",
      "publish_year": "2016"
    }
  ]
}
