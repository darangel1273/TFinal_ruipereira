package com.ruipereira.tfinal_ruipereira.bd

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ruipereira.tfinal_ruipereira.classesAuxiliares.Contacto

/**
 * Classe Controladora Driver que gere o acesso à Base de dados:"contactos.db" - SQLite
 *
 * @author  Rui Pereira
 *
 * @see SQLiteOpenHelper
 */
const val uriEstatica = "android.resource://tfinal_ruipereira/drawable/semfoto"
class DBHelper : SQLiteOpenHelper {
    private var name: String = "contactos.db"
    private var versao: Int = 0// private var db : SQLiteDatabase? =null ;
    private var criarSQL = arrayOf(
        "BEGIN TRANSACTION;",
        "CREATE TABLE IF NOT EXISTS contacto (cc TEXT NOT NULL PRIMARY KEY,nome TEXT NOT NULL,morada TEXT NOT NULL,nascimento TEXT NOT NULL,sexo TEXT NOT NULL,telefone TEXT NOT NULL,telemovel TEXT NOT NULL,email TEXT NOT NULL, foto BLOB NULL);",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('1','Alfa','NY','1983-01-01','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('2','Beta','WSH','1984-02-01','F','00351911791682','00351249093166','ruipereira@aeourem.pt')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('3','Charlie','CAL ','1985-02-02','M','00351911791682','00351249093166','rui_o_pereira@hotmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('4','Delta','MON','1986-03-04','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('5','Echo','ILI','1983-05-06','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('6','FoxTrot','TX','1983-06-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('7','Golf','COL','1984-07-07','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('8','Hotel','HW','1984-08-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('9','India','FL','1985-09-07','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('10','Juliet','AL','1986-10-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('11','Kilo','NV','1987-11-07','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('12','Lima','NM','1988-12-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('13','Mike','OH','1989-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('14','November','VN','1989-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('15','Oscar','GEO','1989-02-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('16','Papa','MASS','1990-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('17','Quebec','NH','1991-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('18','Romeo','PN','1992-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('19','Sierra','AZ','1993-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('20','Tango','NC','1994-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('21','Uniform','NJ','1995-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('22','Victor','OR','1996-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('23','Whiskey','UTH','1997-12-09','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('24','Xray','MISS','1998-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('25','Yankee','MR','2000-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email') VALUES ('10214021','Rui Manuel de Oliveira Pereira','Ourém','1973-06-12','M','00351911791682','00351249093166','rui.o.pereira@gmail.com')",
        "END TRANSACTION;"
    )

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?) :
            super(context, name, factory, 1) {
        if (name != null) {
            this.name = name
        }
    }

    /**
     * Carrega a ListView com a tabela dos contactos
     * @see     SQLiteOpenHelper
     * @param   ArrayList<E>
     */
    @SuppressLint("Range")
    fun carregar(): ArrayList<Contacto> {
        var ret: ArrayList<Contacto> = ArrayList()
        var c: Cursor
        try {
            c = readableDatabase.rawQuery("SELECT * FROM contacto", null)
            c.moveToFirst()
            do {
                ret.add(
                    Contacto(
                        c.getString(c.getColumnIndex("cc")),
                        c.getString(c.getColumnIndex("nome")),
                        c.getString(c.getColumnIndex("morada")),
                        c.getString(c.getColumnIndex("nascimento")),
                        c.getString(c.getColumnIndex("sexo")),
                        c.getString(c.getColumnIndex("telefone")),
                        c.getString(c.getColumnIndex("telemovel")),
                        c.getString(c.getColumnIndex("email"))
                    )
                )
            } while (c.moveToNext())
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return ret
        }
    }

    /**
     * método-função que apaga um registo da tabela contacto da base de dados
     *
     * @see     SQLiteOpenHelper
     * @param   Contacto
     * @return  Boolean
     */
    fun apaga(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.delete("contacto", "cc=?", arrayOf(c.getCC())); ret = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return ret
        }
    }

    /**
     * Função que insere um contacto novo na Base de dados
     * @see     SQLiteOpenHelper
     * @param   Contacto
     * @return  Boolean
     */
    fun insere(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.insert("contacto", null, c.toCV());ret = true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return ret
        }
    }

    /**
     * Função que Actualiza um contacto na Base de dados
     * @see     SQLiteOpenHelper
     * @param   Contacto
     * @return  Boolean
     */
    fun atualiza(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.update("contacto", c.toCV(), "cc=?", arrayOf(c.getCC()))
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return ret
        }
    }

    /**
     * Vai verificar se o cc já existe na tabela (INSERIR)
     * @see     Contacto
     * @param   String
     * @return  Boolean
     */
    fun jaExiste(bi: String): Boolean {
        var ret = false
        val c: Cursor =
            readableDatabase.rawQuery("SELECT cc FROM contacto WHERE cc=?;", arrayOf(bi))
        if (c.moveToFirst()) ret = true
        return ret
    }

    /**
     * Função que cria a tabela de contacto e a enche com alguns registos iniciais
     *
     * @see SQLiteDatabase
     * @param   db
     */
    override fun onCreate(db: SQLiteDatabase?) {
        for (s: String in criarSQL)
            db?.execSQL(s)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        versao++
        db?.execSQL("DROP TABLE IF EXISTS contacto;")
        onCreate(db)
    }

    /**
     * Carrega para a #ListView, o #ArrayList com os #Contactos (DADOS ESTATICOS)
     * @deprecated agora com bd não é precisa
     * @see     ArrayList     Informação dos Contactos
     * @param   #Não recebe parâmetros
     * @return  #Não retorna parâmetros
     */
    @Deprecated("Substituição com a implementação da Base de dados", ReplaceWith("carregar()"))
    fun load2List(): ArrayList<Contacto> {
        val ret: ArrayList<Contacto> = ArrayList()
        ret.add(
            Contacto(
                "01",
                "Rui Manuel de Oliveira Pereira",
                "Ourém, Portugal",
                "1973-06-12",
                "M",
                "00351911791682",
                "00351249093166",
                "rui_o_pereira@hotmail.com"
            )
        )
        ret.add(
            Contacto(
                "02",
                "Ana Paula de Oliveira Pereira",
                "Donegal, Ireland",
                "1981-05-18",
                "F",
                "00351911791682",
                "00351249093166",
                "anabex@hotmail.com"
            )
        )
        ret.add(
            Contacto(
                "03",
                "Artur Pinto",
                "Lisboa, Portugal",
                "1990-07-03",
                "M",
                "00351911791682",
                "00351249093166",
                "anabex@hotmail.com"
            )
        )
        ret.add(
            Contacto(
                "04",
                "Sandra Lopes",
                "Ourém, Portugal",
                "1973-06-02",
                "F",
                "00351911791682",
                "00351249093166",
                "sandralopes027@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "05",
                "John Doe",
                "Baltimore, USA",
                "1900-01-01",
                "M",
                "00351911791682",
                "00351249093166",
                "nobody@gone.com"
            )
        )
        ret.add(
            Contacto(
                "06",
                "Jane Doe",
                "Colorado, USA",
                "1900-01-01",
                "F",
                "00351911791682",
                "00351249093166",
                "unknown@gone.com"
            )
        )
        ret.add(
            Contacto(
                "07",
                "Alpha",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "08",
                "Bravo",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "09",
                "Charlie",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "10",
                "Delta",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "11",
                "Eco",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "12",
                "FoxTrot",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "13",
                "Golf",
                "mu",
                "2002-09-05",
                "F",
                "00351911791682",
                "00351249093166",
                "rui.o.pereira@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "14",
                "Hotel",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "15",
                "India",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "16",
                "Juliet",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "17",
                "Kilo",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "18",
                "Lima",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "19",
                "Mike",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "20",
                "November",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "21",
                "Oscar",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "22",
                "Papa",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "23",
                "Quebec",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "24",
                "Romeo",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "25",
                "Sierra",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "26",
                "Tango",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "27",
                "Uniform",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "28",
                "Victor",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "29",
                "Whiskey",
                "mu",
                "2002-09-05",
                "M",
                "00351911791682",
                "00351249093166",
                "ruipereira@aeourem.pt"
            )
        )
        ret.add(
            Contacto(
                "30",
                "Xray",
                "Montana, USA",
                "2001-04-06",
                "M",
                "00351911791682",
                "97",
                "aportu@gmail.com"
            )
        )
        ret.add(
            Contacto(
                "31",
                "Zulu",
                "Montana, USA",
                "2001-04-06",
                "M",
                "00351911791682",
                "97",
                "aportu@gmail.com"
            )
        )
        return ret
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    /**
     * Vai buscar o Maior cc da tabela (INSERIR)
     */
    @Deprecated("O CC deixou de ser numero")
    fun maxCC(): String {
        val c: Cursor = readableDatabase.rawQuery("SELECT MAX(cc) FROM contacto;", null)
        c.moveToFirst()
        return c.getString(0)
    }
}