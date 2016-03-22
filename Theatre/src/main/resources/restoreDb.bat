C:
cd %1
mysql --user=%2 --password=%3 -e "create database %4" 
mysql --user=%2 --password=%3  %4<%5
EXIT 0