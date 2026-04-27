# Tienda Virtual — TDD + ORM

**Producto Académico N.° 3 — Construcción de Software**  
Universidad Continental | Mg. Waldyr Fredy Cerron Valverde

---

## Descripción

Proyecto Java que implementa una tienda virtual aplicando:

- ✅ **Pruebas unitarias** con JUnit 5
- ✅ **Desarrollo Guiado por Pruebas (TDD)** — ciclo Red → Green → Refactor
- ✅ **Katas TDD** — Carrito de Compras con iteraciones incrementales
- ✅ **ORM con JPA / Hibernate** — persistencia con H2 en memoria para pruebas
- ✅ **Mocks con Mockito** — aislamiento de la capa de servicio

---

## Estructura del proyecto

```
tienda-tdd/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/tienda/
    │   │   ├── modelo/
    │   │   │   ├── Producto.java          ← Entidad JPA
    │   │   │   └── Carrito.java           ← Kata TDD
    │   │   ├── service/
    │   │   │   └── ProductoService.java   ← Lógica de negocio
    │   │   └── repositorio/
    │   │       ├── ProductoRepository.java       ← Interfaz
    │   │       └── ProductoRepositoryImpl.java   ← Implementación JPA
    │   └── resources/META-INF/
    │       └── persistence.xml            ← Configuración Hibernate + H2
    └── test/
        └── java/com/tienda/
            ├── modelo/
            │   ├── ProductoTest.java       ← TDD: entidad
            │   └── CarritoTest.java        ← Kata TDD
            ├── service/
            │   └── ProductoServiceTest.java ← Mockito
            └── repositorio/
                └── ProductoRepositoryIntegrationTest.java ← JPA + H2
```

---

## Requisitos

- Java 11+
- Maven 3.6+

---

## Ejecutar pruebas

```bash
# Clonar el repositorio
git clone https://github.com/<tu-usuario>/tienda-tdd.git
cd tienda-tdd

# Ejecutar todos los tests
mvn test

# Ver reporte de cobertura
mvn verify
```

---

## Ciclo TDD aplicado

| Fase | Descripción |
|------|-------------|
| 🔴 **Red**     | Se escribe la prueba antes del código. Falla obligatoriamente. |
| 🟢 **Green**   | Se implementa el código mínimo para pasar la prueba. |
| 🔵 **Refactor**| Se limpia y mejora el código sin romper las pruebas. |

---

## Pruebas incluidas

| Clase de test | Tipo | Herramienta |
|---|---|---|
| `ProductoTest` | Unitaria | JUnit 5 |
| `CarritoTest` | Kata TDD | JUnit 5 |
| `ProductoServiceTest` | Unitaria con mock | JUnit 5 + Mockito |
| `ProductoRepositoryIntegrationTest` | Integración ORM | JUnit 5 + Hibernate + H2 |

---
## Integrante
Ramos Escobar Crist Antony
