/*******************************************************************************
 ******************************************************************************/
package com.msdp.worker.utils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class CapiRandomUuidGenerator 
{
    private static final CapiRandomUuidGenerator _SINGLETON = new CapiRandomUuidGenerator();
    
    private static final char[] _HEX_VALUES = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'a', 'b', 'c', 'd', 'e', 'f'
    };
    
    //
    // Used to create all random numbers - guarantees unique strings 
    // during the process lifetime
    //
    private static SecureRandom _RNG;
    
    /**
     * 
     * The default constructor is explicit so we can make it private and 
     * require use of getInstance() for instantiation.
     * 
     * @see #getInstance()
     *
     */
    private CapiRandomUuidGenerator()
    {
        //
        // this is just to prevent instantiation
        //
    	Long ntl = new Long(System.nanoTime());
    	byte[] bytes = ByteBuffer.allocate(8).putLong(ntl).array(); 
    	_RNG = new SecureRandom(bytes);
    	
    }
    
    /**
     * 
     * @return A unique UUID of the form <em>uuid:<b>X</b></em>, where 
     *        <b>X</b> is the generated value.
     *
     */
    public String createUUID()
    {
        //
        // first get 16 random bytes...
        //
        byte[] bytes = new byte[16];
        _RNG.nextBytes(bytes);
        
        StringBuffer uuid = new StringBuffer(41);
        //uuid.append("uuid:");
        
        //
        // ...then we need to shift so they're valid hex (0-9, a-f)
        //
        for (int n = 0; n < 16; ++n) 
        {
            //
            // (there's really no elegant way to add the dashes...)
            //
            if (n == 4 || n == 6 || n == 8 || n == 10)
                uuid.append('-');
            
            //
            // shift the bits so they are proper hex
            //
            int hex = bytes[n] & 255;
            uuid.append(_HEX_VALUES[hex >> 4]);
            uuid.append(_HEX_VALUES[hex & 15]);
        }
        
        return uuid.toString();
    }
    
    /**
     * 
     * @return The singleton instance of this class.
     *
     */
    public static CapiRandomUuidGenerator getInstance()
    {
        return _SINGLETON;
    }   
   
}
