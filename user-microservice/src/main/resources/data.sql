insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (1, 'henryadmin1', 'henry123', 'henry123', 'Henry', 'Henry', '1111111111', 'henry@abc.com', 'Admin');
insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (2, 'olivertrainer1', 'oliver123', 'oliver123', 'Oliver', 'Oliver', '2222222222', 'oliver@abc.com', 'Trainer');
insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (3, 'jamestrainer2', 'james123', 'james123', 'James', 'James', '3333333333', 'james@abc.com', 'Trainer');
insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (4, 'noahuser1', 'noah123', 'noah123', 'Noah', 'Noah', '4444444444', 'noah@abc.com', 'User');
insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (5, 'elijahuser2', 'elijah123', 'elijah123', 'Elijah', 'Elijah', '5555555555', 'elijah@abc.com', 'User');
insert into users (userId, userName, password, confirmPassword, firstName, lastName, contactNumber, emailId, userType) values (6, 'lucasuser3', 'lucas123', 'lucas123', 'Lucas', 'Lucas', '6666666666', 'lucas@abc.com', 'User'); 

insert into UserTrainer (transactionId, userId, trainerId) values (1, 4, 2);
insert into UserTrainer (transactionId, userId, trainerId) values (1, 5, 3);
insert into UserTrainer (transactionId, userId, trainerId) values (1, 6, 2);
