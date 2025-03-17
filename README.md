# web-testing-tool
Tool for executing selenium commands on a given target by following a JSON descriptor.

The idea is to be able to run a suite of Selenium tests just by following some commands written in JSON format.

For example, a test could be created and executed by following a JSON like the following:

{
  "test_id": "test_1",
  "test_name": "First test",
  "test_description": "The first test",
  "test_global_vars": {
    "baseUrl": "www.example.com",
    "username": "username",
    "password": "password"
  },
  "test_items": [
    {
      "name": "open_new_tab",
      "description": "Open new tab",
      "in_vars": {
        "url": "www.google.gr"
      },
      "out_vars": {
        "new_tab_id": "${first_tab_id}"
      },
      "command": "open_new_tab",
      "run_status": "not run",
      "result_message": ""
    },
    {
      "name": "open_new_tab",
      "description": "Open new tab",
      "in_vars": {
        "url": "www.google.com"
      },
      "out_vars": {
        "new_tab_id": "${second_tab_id}"
      },
      "command": "open_new_tab",
      "run_status": "not run",
      "result_message": ""
    }
  ],
  "test_result": ""
}

The project is work in progress and does not handle yet Selenium commands.
