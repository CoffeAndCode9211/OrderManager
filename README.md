# OrderManager
OrderManager is build on Spring Boot and MySQL Database.
This App expose API to manage Item and it's Order.

### REST APIs

- Item: ``http://localhost:9988/api/item`` ( GET , POST , PUT , DELETE )

- Order: ``http://localhost:9988/api/order``( GET , POST , DELETE )

### Configuration

- MySql configuration can be changed in ``application.properties`` file.
- In Resource folder we have ``query.sql`` which contains the DB query.
- Also find ``OrderMaster.postman_collection.json`` in resource folder which is set of APIs which can be imported in Postman.
- Just download the json file and import it in Postman to see all APIs.

### Cases
Order Management APIs handles following cases
- Check for any Active Order, before Deleting an Item and return msg to User.
- Can Order 1 or more Pieces of same Item.
- Check for Out of Stock while adding Order and show error to User.
- Update Item when Order is Deleted
