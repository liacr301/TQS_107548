from datetime import datetime, timedelta
import json
import random

# Lista de cidades
cidades = ["Aveiro", "Lisboa", "Viseu", "Porto", "Braga", "Guarda"]

def gerar_datas_aleatorias(n):
    base = datetime.now()
    return [(base + timedelta(days=random.randint(0, 10))).strftime('%Y-%m-%d') for _ in range(n)]

viagens = []
viagem_id = 1

for origem in cidades:
    for destino in cidades:
        if origem != destino:  
            datas_aleatorias = gerar_datas_aleatorias(5)
            for i in range(1, 6):
                bus_number = random.randint(100, 600)
                num_seats = random.randint(20, 40)
                horarios = [{
                    "ID": f"{viagem_id:03}",
                    "Bus": f"Bus {bus_number}",
                    "Date": f"{datas_aleatorias[i-1]}",  
                    "Dia de Hour": f"{8 + i}:00",                  
                    "Preço": f"{20 + i * 5}€",
                    "Seats": f"{num_seats}"
                }]
                viagem = {
                    "from": origem,
                    "to": destino,
                    "trips": horarios
                }
                viagens.append(viagem)
                viagem_id += 1

jsonbus = {
    "busdemo": viagens
}

viagens_json = json.dumps(jsonbus, indent=4)
with open("bus.json", "w") as arquivo:
    arquivo.write(viagens_json)

print(viagens_json)