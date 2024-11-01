import pandas as pd
import matplotlib.pyplot as plt

# Ler os resultados do arquivo CSV
df = pd.read_csv('Resultados.csv')

# Verificar as colunas do DataFrame
print("Colunas disponíveis:", df.columns)

# Remover espaços em branco nos nomes das colunas
df.columns = df.columns.str.strip()

# Definir os algoritmos a serem plotados
algoritmos = df['Algoritmo'].unique()

# Criar um gráfico para cada algoritmo
for algoritmo in algoritmos:
    # Filtrar os dados para o algoritmo atual
    data = df[df['Algoritmo'] == algoritmo]

    plt.figure(figsize=(10, 6))
    plt.title(f'Tempo de Execução para {algoritmo}')
    plt.xlabel('Tamanho do Array')
    plt.ylabel('Tempo (s)')

    # Plotar os dados para cada semente
    for seed in data['Semente'].unique():
        seed_data = data[data['Semente'] == seed]
        plt.plot(seed_data['Tamanho'], seed_data['Tempo(ns)'], marker='o', label=f'Semente {seed}')

    plt.legend()
    plt.grid()
    plt.savefig(f'{algoritmo.replace(" ", "_")}.png')  # Salvar o gráfico como imagem
    plt.show()
