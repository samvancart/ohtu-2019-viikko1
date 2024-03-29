package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisätäLiikaa() {
        varasto.lisaaVarastoon(11);
        // varaston saldo ei voi ylittää tilavuutta
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(9);
        // varaston saldo ei voi olla < 0
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikein() {
        varasto.lisaaVarastoon(1);

        assertEquals("saldo = 1.0, vielä tilaa 9.0", varasto.toString());
    }

    @Test
    public void varastoAlkuSaldollaPalauttaaOikeanSaldon() {
        varasto = new Varasto(10, 2);

        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoAlkuSaldollaPalauttaaOikeanTilavuuden() {
        varasto = new Varasto(10, 2);

        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonAlkuSaldoEiVoiOllaNegatiivinen() {
        varasto = new Varasto(10, -1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonAlkuSaldoEiVoiYlittääTilavuutta() {
        varasto = new Varasto(10, 11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    //varasto alkusaldolla
    
    public void varastonTilavuusEiVoiOllaNegatiivinen() { 
        varasto = new Varasto(-1, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
     //varasto ilman alkusaldoa
    public void varastonTilavuusEiVoiOllaNegatiivinen2() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisätäNegatiivista() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivista() {
        varasto.otaVarastosta(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
}
