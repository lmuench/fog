# :foggy: Gateway for a Fog Computing Ecosystem :foggy:
Fog computing minimizes the amount of data sent to the cloud by pre-processing, aggregating and filtering at the logical edge.

The gateway can be used integrate devices of any protocol and create adapters for else incompatible interfaces.

Its web UI lets you create a REST API for resources registered in the connected [Resource Directory](https://datatracker.ietf.org/doc/draft-ietf-core-resource-directory) (RD), choose which resources are exposed and how data is locally pre-processed.

You can easily extend the gateway with support of new protocols or functionality by writing your own modules. You can install these modules through the web UI, and thanks to the underlying OSGi platform, replace modules at runtime.

## System Overview
![system-overview](https://user-images.githubusercontent.com/28816371/52897417-7064e900-31d4-11e9-8042-972b20352e1c.png)

## Web UI Usage
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
