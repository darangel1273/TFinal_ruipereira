package com.ruipereira.tfinal_ruipereira.bd

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ruipereira.tfinal_ruipereira.classesAuxiliares.Contacto

/**
 * Classe Controladora - Driver que gere o acesso à Base de dados:"contactos.db" - SQLite
 *
 * @author  Rui Pereira
 *
 * @see SQLiteOpenHelper
 */
const val uriEstatica = "android.resource://com.ruipereira.tfinal_ruipereira/R.drawable/semfoto"
class DBHelper : SQLiteOpenHelper {
    private var name: String = "contactos.db"
    private var versao: Int = 0        // private var db : SQLiteDatabase? =null ;
    private var criarSQL = arrayOf(
        "BEGIN TRANSACTION;",           //CCs retirados de https://cc.marcosantos.me/
        "CREATE TABLE IF NOT EXISTS contacto (cc TEXT NOT NULL PRIMARY KEY,nome TEXT NOT NULL,morada TEXT NOT NULL,nascimento TEXT NOT NULL,sexo TEXT NOT NULL,telefone TEXT NOT NULL,telemovel TEXT NOT NULL,email TEXT NOT NULL, uri TEXT, foto BLOB);",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('13936498 6 ZZ0','Alfa','NY','1983-01-01','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('39696357 9 ZY6','Beta','WSH','1984-02-01','F','00351911791682','00351249093166','ruipereira@aeourem.pt','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('34903924 0 ZW8','Charlie','CAL ','1985-02-02','M','00351911791682','00351249093166','rui_o_pereira@hotmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('37493813 0 ZX9','Delta','MON','1986-03-04','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('15982701 9 ZY1','Echo','ILI','1983-05-06','M','00351911791682','00351249093166','ruipereira@aeourem.pt','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('34548234 4 ZZ2','FoxTrot','TX','1983-06-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('36981511 4 ZX1','Golf','COL','1984-07-07','M','00351911791682','00351249093166','ruipereira@aeourem.pt','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('31077995 2 ZY0','Hotel','HW','1984-08-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('32602937 0 ZW3','India','FL','1985-09-07','M','00351911791682','00351249093166','ruipereira@aeourem.pt','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('36429115 0 ZV3','Juliet','AL','1986-10-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('11689914 0 ZV4','Kilo','NV','1987-11-07','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('16921503 2 ZZ1','Lima','NM','1988-12-07','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('19473530 3 ZW1','Mike','OH','1989-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('18649267 7 ZY3','November','VN','1989-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('11164412 7 ZV0','Oscar','GEO','1989-02-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('19991700 0 ZX0','Papa','MASS','1990-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('14067733 0 ZZ1','Quebec','NH','1991-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('38408901 1 ZV1','Romeo','PN','1992-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('11196951 4 ZZ8','Sierra','AZ','1993-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('37492822 3 ZW6','Tango','NC','1994-12-08','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('17073366 1 ZX2','Uniform','NJ','1995-12-08','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('32895776 3 ZZ5','Victor','OR','1996-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('36836227 2 ZY4','Whiskey','UTH','1997-12-09','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('38449249 5 ZX3','Xray','MISS','1998-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('16209273 3 ZY9','Yankee','MR','2000-12-09','F','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('10214021 9 ZX9','Rui Manuel de Oliveira Pereira','Ourém','1973-06-12','M','00351911791682','00351249093166','rui.o.pereira@gmail.com','$uriEstatica','R.drawable.semfoto');",
        "INSERT INTO 'contacto' ('cc','nome','morada','nascimento','sexo','telefone','telemovel','email','uri','foto') VALUES ('08208245 1 ZZ0','Sandra Paula Marques Lopes','Ourém','1968-05-27','F','00351916940522','00351249157946','sandralopes027@gmail.com','$uriEstatica','R.drawable.semfoto');",
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
     *
     * @see     SQLiteOpenHelper
     * @param   ArrayList<E>
     * @throws  SQLiteException
     */
    fun carregar(): ArrayList<Contacto> {
        var ret: ArrayList<Contacto> = ArrayList()
        var c: Cursor
        try {
            c = readableDatabase.rawQuery("SELECT * FROM contacto;", null)
            c.moveToFirst()
            do {                                    // Faz sempre a 1ª Vez
                ret.add(
                    Contacto(
                        c.getString(c.getColumnIndexOrThrow("cc")),
                        c.getString(c.getColumnIndexOrThrow("nome")),
                        c.getString(c.getColumnIndexOrThrow("morada")),
                        c.getString(c.getColumnIndexOrThrow("nascimento")),
                        c.getString(c.getColumnIndexOrThrow("sexo")),
                        c.getString(c.getColumnIndexOrThrow("uri")),
                        c.getString(c.getColumnIndexOrThrow("telefone")),
                        c.getString(c.getColumnIndexOrThrow("telemovel")),
                        c.getString(c.getColumnIndexOrThrow("email"))
                    )
                )
            } while (c.moveToNext())  // Enquanto houver registos no Cursor
            c.close()
        } catch (erro: IllegalArgumentException) {
            erro.printStackTrace()
        } catch (erroSQL: SQLiteException) {
            erroSQL.printStackTrace()
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
     * @throws SQLiteException
     */
    fun apaga(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.delete("contacto", "cc=?", arrayOf(c.getCC())); ret = true
        } catch (e: SQLiteException) {
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
     * @throws  SQLiteException
     */
    fun insere(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.insert("contacto", null, c.toCV());ret = true
        } catch (e: SQLiteException) {
            e.printStackTrace()
        } finally {
            return ret
        }
    }
    /**
     * Função que Actualiza um contacto na Base de dados
     * NOTA: Se o CC Não for alterado no Detalhe (Form),
     * ( Se o CC for alterado, Não Funciona)
     * @see     SQLiteOpenHelper
     * @param   Contacto
     * @return  Boolean
     * @throws  SQLiteException
     */
    fun atualiza(c: Contacto): Boolean {
        var ret = false
        try {
            writableDatabase.update("contacto", c.toCV(), "cc=?", arrayOf(c.getCC()))
        } catch (e: SQLiteException) {
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
     * @throws  SQLiteException
     */
    fun jaExiste(bi: String): Boolean {
        var ret = false
        try {
            val c: Cursor =
                readableDatabase.rawQuery("SELECT cc FROM contacto WHERE cc=?;", arrayOf(bi))
            if (c.moveToFirst()) ret = true;c.close()
        } catch (erroSQL: SQLiteException) {
            erroSQL.printStackTrace()
        } finally {
            return ret
        }
    }

    /**
     * Função que cria a tabela de contacto e a enche com alguns registos iniciais
     *
     * @see SQLiteDatabase
     * @param   db
     * @throws  erroSQL
     * @throws  SQLiteException
     */
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            for (s: String in criarSQL) db?.execSQL(s)
        } catch (erroSQL: SQLiteException) {
            Log.e("CREATE", "Erro a criar a tabela");erroSQL.printStackTrace()
        } catch (erro: Exception) {
            erro.printStackTrace()
        }
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
    @Deprecated("Substituição com a implementação da Base de dados")//, ReplaceWith("carregar()"))
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
    @Deprecated("O CC deixou de ser numero sequencial")
    fun maxCC(): String {
        val c: Cursor = readableDatabase.rawQuery("SELECT MAX(cc) FROM contacto;", null)
        c.moveToFirst()
        return c.getString(0)
    }
}