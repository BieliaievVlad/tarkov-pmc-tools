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
    inspectImageLink
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
    inspectImageLink
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
          inspectImageLink
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
        }
      }
    }
  }
}
			
""";
}
