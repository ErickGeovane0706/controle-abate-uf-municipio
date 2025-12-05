# 🐄 Sistema de Gerenciamento de Abates — Spring Boot + JPA + JDBC Batch

Este projeto implementa um sistema completo para processamento, cadastro e consultas de dados de abates no Brasil.  
Ele combina **JPA/Hibernate**, **JDBC otimizado com batch**, **normalização**, **validação robusta**, e **consultas agregadas** com projeções usando `Record`.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **OpenCSV**
- **JDBC Batch Processing**
- **PostgreSQL / MySQL** (compatível com qualquer SQL relacional)
- **Padronização e Validação Customizada**

## 📁 Estrutura do Projeto

```text
src/
└── main/
    └── java/
        └── ifpb/bancoDeDados/BancodeDados
            ├── controller/
            │   └── AbateController.java
            ├── entity/
            │   ├── Abate.java
            │   ├── CategoriaAnimal.java
            │   ├── Municipio.java
            │   └── UF.java
            ├── record/
            │   ├── SomaPorUfRecord.java
            │   ├── SomaPorAnoRecord.java
            │   ├── SomaPorCategoriaRecord.java
            │   ├── SomaPorMunicipioRecord.java
            │   └── SomaFiltradaRecord.java
            ├── repository/
            │   ├── AbateRepository.java
            │   ├── CategoriaAnimalRepository.java
            │   ├── MunicipioRepository.java
            │   ├── UFRepository.java
            │   └── JDBC/AbateJdbcRepository.java
            ├── service/
            │   ├── AbateService.java
            │   ├── CategoriaAnimalService.java
            │   ├── MunicipioService.java
            │   ├── UFService.java
            │   └── CsvImportJdbcService.java
            └── service/validation/
                ├── AnoValidoRule.java
                ├── MesValidoRule.java
                ├── UfValidaRule.java
                ├── QuantidadeValidaRule.java
                ├── ValidationRule.java
                ├── ValidationConfig.java
                └── ValidatorEngine.java
```

## 🧩 Funcionalidades Principais

### ✔ Importação de CSV via JDBC  
- Extremamente rápida  
- Usa prepared statements + batch  
- Validação linha a linha  
- Normalização de categorias  
- Cache para evitar SELECTs repetidos  

### ✔ Cadastro automático de:
- UF  
- Município  
- Categoria Animal  

### ✔ Endpoints completos de consulta:
- Consultas básicas  
- Consultas agregadas com projeção (`Record`)  
- Filtros combinados  

### ✔ Validação completa:
- Ano válido  
- Mês válido  
- UF válida  
- Quantidade positiva  

### ✔ Normalização flexível de categorias (plugin-like)
- Baseada em `ServiceLoader`  
- Permite múltiplos providers de forma extensível  

---

# 📡 Endpoints da API

## 🔹 Listar todos os abates
`GET /api/abates`

## 🔹 Consultas Básicas

### Buscar por ano
`GET /api/abates/ano/{ano}`

### Buscar por ano e mês
`GET /api/abates/ano/{ano}/mes/{mes}`

### Buscar por UF
`GET /api/abates/uf/{uf}`

### Buscar por município
`GET /api/abates/municipio/{municipio}`

### Buscar por categoria
`GET /api/abates/categoria/{categoria}`

---

## 📊 Consultas Agregadas

### Soma por UF
`GET /api/abates/soma-por-uf`

**Retorno:**
```json
[
  {"uf": "PB", "total": 12345},
  {"uf": "SP", "total": 9988}
]
```

### Soma por Município
`GET /api/abates/soma/municipio`

### Soma por Categoria
`GET /api/abates/soma/categoria`

### Soma por Ano
`GET /api/abates/soma/ano`

### 🎯 Consulta Filtrada (qualquer combinação)
`GET /api/abates/soma-filtrada`

**Parâmetros opcionais:**

| Parâmetro | Tipo | Exemplo |
| :--- | :--- | :--- |
| `uf` | String | PB |
| `categoria` | String | Bovino |
| `ano` | Int | 2020 |
| `mes` | Int | 5 |

**Exemplo:**
`GET /api/abates/soma-filtrada?uf=PB&categoria=Bovino&ano=2020`

**Retorno:**
```json
{ "total": 812 }
```

---

## 📥 Importação de CSV via JDBC (Ultra Rápida)

O serviço `CsvImportJdbcService` realiza:
1. Validação linha a linha
2. Normalização
3. Inserções em batch
4. Cache para FK
5. Transações manuais

**Exemplo de uso:**
```java
csvImportJdbcService.importarCsvJdbc("C:\\dados\\abates.csv");
```

---

## 🧪 Regras de Validação

As regras são aplicadas no `ValidatorEngine`.

| Regra | Classe |
| :--- | :--- |
| Ano deve ser entre 1900 e 2100 | `AnoValidoRule` |
| Mês deve ser entre 1 e 12 | `MesValidoRule` |
| UF deve ser válida | `UfValidaRule` |
| Quantidade ≥ 0 | `QuantidadeValidaRule` |

---

## 🗃 Modelo de Banco de Dados

`UF (1) ---- (N) Municipio ---- (N) Abate ---- (1) CategoriaAnimal`

### 📌 Exemplo de Registro no CSV
```csv
ano;mes;uf;municipio;categoria;quantidade
2020;05;PB;Cajazeiras;Bovino;213
```

---

## 🧑‍💻 Como Rodar o Projeto

```bash
# Clonar repositório
git clone [https://github.com/seuusuario/seu-repo.git](https://github.com/seuusuario/seu-repo.git)

# Entrar na pasta
cd seu-repo

# Rodar
mvn spring-boot:run
```
