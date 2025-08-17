package com.bieliaiev.tarkov_pmc_tools.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Queries {

	public static final String ALL_AMMO = 
"""
{
  items (categoryNames: [Ammo]) {
    id
    name
    normalizedName
    description
    gridImageLink
    image8xLink
    buyFor{
      vendor {
        name
      }
      price
      currency
    }
    properties {
      ...on ItemPropertiesAmmo {
        caliber
        tracer
        tracerColor
        ammoType
        projectileCount
        damage
        armorDamage
        penetrationPower
        accuracyModifier
        recoilModifier
        initialSpeed
        lightBleedModifier
        heavyBleedModifier
        heatFactor
        ballisticCoeficient
        bulletDiameterMilimeters
        bulletMassGrams
      }
    }
  }
}
""";
	
	public static final String ALL_WEAPONS = 
"""
{
  items(categoryNames: [Weapon]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink
    category{
      name
      normalizedName
    }
    properties{
      __typename
      ...on ItemPropertiesPreset{
        ergonomics
        recoilVertical
        recoilHorizontal
        moa
        default
      }
    }
    properties {
      __typename
      ... on ItemPropertiesWeapon {
        caliber
        ergonomics
        fireModes
        fireRate
        recoilVertical
        recoilHorizontal
        presets{
          name
          shortName
          normalizedName
          gridImageLink
          image8xLink
          properties{
            __typename
            ...on ItemPropertiesPreset{
              ergonomics
              recoilVertical
              recoilHorizontal
              moa
              default
            }
          }
          containsItems{
            ...on ContainedItem{
              item{
                name
                shortName
                normalizedName
                gridImageLink
                image8xLink
                description
              }
            }
          }
        }
      }
    }
  }
}
			
""";
	
	public static final String ALL_MELEE = 
"""
{
  items(categoryNames: [Knife]){
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink
    properties{
      ...on ItemPropertiesMelee{
        slashDamage
        stabDamage
        hitRadius
      }
    }
  }
}			
""";
	
	public static final String ALL_GRENADES = 
"""
{
  items(categoryNames: [ThrowableWeapon]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink
    properties {
      ... on ItemPropertiesGrenade {
        type
        fuse
        minExplosionDistance
        maxExplosionDistance
        fragments
        contusionRadius
      }
    }
  }
}			
""";
	
	public static final String ALL_HEADPHONES = 
"""
{
  items(categoryNames: [Headphones]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink
    properties {
      ... on ItemPropertiesHeadphone {
        ambientVolume
        compressorAttack
        compressorGain
        compressorRelease
        compressorThreshold
        distanceModifier
        distortion
        dryVolume
      }
    }
  }
}		
""";
	
	public static final String ALL_ARMBANDS = 
"""
{
  items(categoryNames: [ArmBand]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink   
  }
}
""";
	
	public static final String ALL_ARMOR = 
"""
{
  items(categoryNames: [Armor]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink 
        properties{
      ...on ItemPropertiesArmor{
        class
        armorType
        zones
      }
    }  
  }
}
""";
	
	public static final String ALL_HEADWEAR = 
"""
{
  items(categoryNames: [Headwear]) {
    name
    shortName
    normalizedName
    description
    gridImageLink
    image8xLink
    properties{
      ...on ItemPropertiesHelmet{
        class
        armorType
        durability
        speedPenalty
        turnPenalty
        ergoPenalty
        deafening
        blocksHeadset
      }
    }
  }
}		
""";
}
