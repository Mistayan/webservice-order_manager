"""
Several json payloads to test our WebService
"""
import json
import requests

########################################################################################################################
# PARAMS
# Change this to fit ur real database
TABLES_KEYS = {
    'article': {
        'nom': 'name',
        'prix': 'unit_price',
        'id': 'item_id'
    },
    'commande': {
        'client': 'customer_name'
    }

}

_URL = "http://127.0.0.1:5017/debug_webservice"

########################################################################################################################
# PAYLOADs

item_list = [
    {
        TABLES_KEYS.get('article').get('nom'): "gibson sg",
        TABLES_KEYS.get('article').get('prix'): 1250.00
    },
    {
        TABLES_KEYS.get('article').get('nom'): "fender stratocaster",
        TABLES_KEYS.get('article').get('prix'): 1100.00
    },
    {
        TABLES_KEYS.get('article').get('nom'): "fender telecaster",
        TABLES_KEYS.get('article').get('prix'): 1200.00
    },
    {
        TABLES_KEYS.get('article').get('nom'): "gibson explorer",
        TABLES_KEYS.get('article').get('prix'): 1000.00
    }, 
    {
        TABLES_KEYS.get('article').get('nom'): "marshall JCM800",
        TABLES_KEYS.get('article').get('prix'): 1700.00
    }
]

item_one = [
    {
        TABLES_KEYS.get('article').get('nom'): "boss overdrive",
        TABLES_KEYS.get('article').get('prix'): 300.00
    }
]

update_one = [
    {
        TABLES_KEYS.get('article').get('id'): 1,
        TABLES_KEYS.get('article').get('prix'): 1500
    }
]

delete_one = [
    {
        TABLES_KEYS.get('article').get('id'): 2,
    }
]

fill_storage = [
    {
        TABLES_KEYS.get('article').get('id'): 1,
        "quantity": 10
    },
    {
        TABLES_KEYS.get('article').get('id'): 3,
        "quantity": 10
    },
    {
        TABLES_KEYS.get('article').get('id'): 4,
        "quantity": 10
    },
    {
        TABLES_KEYS.get('article').get('id'): 5,
        "quantity": 1
    }
]

one_valid_command = [
    {
        TABLES_KEYS.get('command').get('client'): "Bob",
        "orderItems": [{"id": 1}, {"id": 3}]
    }
]

many_valid_command = [
    {
        TABLES_KEYS.get('command').get('client'): "Stephen",
        "orderItems": [{"id": 1}]
    },
    {
        TABLES_KEYS.get('command').get('client'): "Orel",
        "orderItems": [{"id": 1}, {"id": 4}]
    },
    {
        TABLES_KEYS.get('command').get('client'): "Noé",
        "orderItems": [{"id": 3}, {"id": 4}, {"id": 5}]
    }
]

one_not_valid_command = [
    {
        TABLES_KEYS.get('command').get('client'): "Hendrix",
        "orderItems": [{"id": 5}]
    }
]

cmds = {
    "item_list": item_list,
    "item_one": item_one,
    "update_one": update_one,
    "delete_one": delete_one,
    "fill_storage": fill_storage,
    "one_valid_command": one_valid_command,
    "many_valid_command": many_valid_command,
    "one_not_valid_command": one_not_valid_command
}

for cmd_name, cmd in cmds.items():
    try:
        r = requests.post(url=_URL, data=json.dumps(cmd))
    except ValueError as e:
        print('====================')
        print(e)
        print(cmd_name)
        print('====================')
    else:
        print(r)
