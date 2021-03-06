# :foggy: Gateway for a Fog Computing Ecosystem :foggy:
Fog computing minimizes the amount of data sent to the cloud by pre-processing, aggregating and filtering at the logical edge.

The gateway can be used integrate devices of any protocol and create adapters for else incompatible interfaces.

Its web UI lets you create a REST API for resources registered in the connected [Resource Directory](https://datatracker.ietf.org/doc/draft-ietf-core-resource-directory) (RD), choose which resources are exposed and how data is locally pre-processed.

You can easily extend the gateway with support of new protocols or functionality by writing your own modules. You can install these modules through the web UI, and thanks to the underlying OSGi platform, replace modules at runtime.

## System Overview
![system-overview](https://user-images.githubusercontent.com/28816371/52897417-7064e900-31d4-11e9-8042-972b20352e1c.png)

## Web UI Usage
source code: https://github.com/lmuench/fog-ui
### Connect to a gateway and a Resource Directory
![connect](https://user-images.githubusercontent.com/28816371/52898200-3a773300-31db-11e9-9156-8e91b86ddc98.png)
1. Click on the right-most item on the navigation bar to open a dropdown menu
2. On the dropdown menu, click “Edit connections...”
3. Enter both gateway and RD URL
4. Click “Save”

### Create a REST API for resources registered in an RD
![create](https://user-images.githubusercontent.com/28816371/52898276-11a36d80-31dc-11e9-9843-24516e0877d5.png)
1. Click “API Builder” on the navigation bar
2. Enter custom URNs in the “Custom Path” column
3. Check the boxes in front of each resource you want to include in your custom REST API
4. Click “Save selected”

### Access resources
![access](https://user-images.githubusercontent.com/28816371/52898323-89719800-31dc-11e9-9131-2dcaf51dcf55.png)
1. Click “Resources” on the navigation bar to open a dropdown menu
2. On the dropdown menu, either select a specific resource or click on "View all"
3. Use the buttons to send HTTP requests with an optional body ("New Value" column)

### Install/Update/Start/Stop modules
![web-console](https://user-images.githubusercontent.com/28816371/52898388-8c20bd00-31dd-11e9-8b6d-00d04d152592.png)
1. Click “Web Console” on the navigation bar to access the embedded [Felix Web Console](http://felix.apache.org/documentation/subprojects/apache-felix-web-console.html)
2. Use the start/stop button to the right of each module or the "Install/Update..." button at the top-right corner

## Architecture
### High-Level Design
![component-diagram](https://user-images.githubusercontent.com/28816371/52965653-9a213a00-33a5-11e9-86d5-92fd8fb9283c.png)

*Component diagram*

### Dynamic Model View
![activity-diagram-request-handling](https://user-images.githubusercontent.com/28816371/52966015-82968100-33a6-11e9-944d-2efec0e0324e.png)

*Request handling activity diagram*
