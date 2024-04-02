from datetime import datetime, timedelta
import json
import random

# Lista de cidades
cidades = ["Aveiro", "Lisboa", "Viseu", "Porto", "Braga", "Guarda"]

def gerar_datas_aleatorias(n):
    base = datetime.now()
    return [(base + timedelta(days=random.randint(0, 10))).strftime('%Y-%m-%d') for _ in range(n)]

# Informações iniciais para gerar as viagens
viagens = []
viagem_id = 1

for origem in cidades:
    for destino in cidades:
        if origem != destino:  # Garantir que a origem e o destino sejam diferentes
            # Criar 5 viagens para cada par de origem/destino
            datas_aleatorias = gerar_datas_aleatorias(5)
            for i in range(1, 6):
                bus_number = random.randint(100, 600)
                horarios = [{
                    "ID": f"{viagem_id:03}",
                    "Bus": f"Bus {bus_number}",
                    "Date": f"{datas_aleatorias[i-1]}",  
                    "Dia de Hour": f"{8 + i}:00",  # Horários diferentes para cada viagem                    
                    "Preço": f"{20 + i * 5}€"  # Preço diferente para cada viagem
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
# Converter a lista de viagens para JSON
viagens_json = json.dumps(jsonbus, indent=4)
with open("bus.json", "w") as arquivo:
    arquivo.write(viagens_json)
# Mostrar o JSON criado
print(viagens_json)