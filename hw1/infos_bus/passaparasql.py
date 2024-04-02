import json

arquivo_json = 'bus.json'
with open(arquivo_json, 'r') as arquivo:
    # Carrega todo o conteúdo do arquivo JSON
    dados_json = json.load(arquivo)
    # Acessa a lista de viagens na chave "busdemo"
    trips_data = dados_json["busdemo"]

sql_insert_statements = []

for trip in trips_data:
    for t in trip["trips"]:
        bus = t["Bus"].split(" ")[1]  # Extrai o número do ônibus
        from_city = trip["from"]
        to_city = trip["to"]
        date = t["Date"]
        time = t["Dia de Hour"] + ":00"  # Adiciona segundos ao tempo
        price = float(t["Preço"].replace("€", ""))  # Converte o preço para float
        seats = t["Seats"]

        # Cria a declaração INSERT para cada viagem
        sql_insert = f"({bus}, '{from_city}', '{to_city}', '{date}', '{time}', {price}, {seats})"
        sql_insert_statements.append(sql_insert)

# Combina todas as declarações INSERT
full_sql_query = "INSERT INTO TBL_TRIPS (bus, fromCity, toCity, date, time, price, availableSeats) VALUES " + ", ".join(sql_insert_statements) + ";"

print(full_sql_query)
