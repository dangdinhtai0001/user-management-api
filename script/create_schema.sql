DROP database `user_management`;
DROP user 'user_management'@'localhost';

CREATE SCHEMA `user_management` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci ;
CREATE user 'user_management'@'localhost' IDENTIFIED by 'Abc123456';

GRANT ALL ON `user_management`.* TO 'user_management'@'localhost';
GRANT FILE ON *.* TO 'user_management'@'localhost';
FLUSH PRIVILEGES;