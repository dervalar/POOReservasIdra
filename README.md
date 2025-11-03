## Base de datos (MySQL)

**Archivo:** `database/hotel_backup.sql`

### Importar en MySQL Workbench
1. Server → Data Import
2. Import from Self-Contained File → seleccionar `database/hotel_backup.sql`
3. Default Target Schema: `hotel` (crear si no existe)
4. Start Import

### Credenciales por defecto (ajustar si es necesario)
- URL: `jdbc:mysql://localhost:3306/hotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- Usuario: `root`
- Password: `root88`

> Si cambia usuario/password, editar en los DAO (HabitacionDao, PersonaDao, ReservaDao).

---
## Licencia
Este proyecto está licenciado bajo la [Apache License 2.0](./LICENSE).  
(c) 2025 Lisandro Frende, Mariano Scarcella. Todos los derechos reservados.
