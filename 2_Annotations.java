Annotations:

@Entity- use to mark any class as entity
@Table- use to change the table details.
@Id- use to mark column as id(Primary key)
@GeneratedValue- hibernate will automatically generate values for that using an internal sequence. Therefore, we don't have to set it manually.
@Column- can be used to specify column mapping. For example, to change the column name in the associated table in database.
@Transient- This tells hibernate not to save the fields.
@Temporal- @Temporal over a date field tells hibernate the format in which the data needs to be saved.
@Lob- this tells hibernate that this is a large object, not a simple object.
