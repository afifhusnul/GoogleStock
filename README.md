# GoogleStock
Pull intraday historical stock from Google Finance and & store it into MySQL.

This is use for Indonesian Stock exchange (IDX) if you want to use for another exchange then you must change link in GoogleStockDownload -->
String url = "https://www.google.com/finance/historical?q=IDX:" <-- change IDX with your exchange option

# Prepare your DB (Use MySQL or MariaDB
Update properties file --> C:\GoogleStockHibernate\src\main\resources\hibernate.cfg.xml (change the DB or username\password as your current setup then execute 
Table-structure.sql.
Don't forget to


# Compile your target file
command in CLI --> mvn clean package

DONE!

# Execute your 
I've created Google.bat file to execute my jar file but normally you may execute it directly with double click GoogleStock.jar

Enjoy it




