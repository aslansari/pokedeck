package com.aslansari.pokedeck.pokemon.model

data class APIResource(
    val url: String
)

data class NamedAPIResource(
    val name: String,
    val url: String
)

data class Description(
    val description: String,
    val language: NamedAPIResource
)

data class Effect(
    val effect: String,
    val language: NamedAPIResource
)

data class Encounter(
    val minLevel: Int,
    val maxLevel: Int,
    val conditionValues: List<NamedAPIResource>,
    val chance: Int,
    val method: NamedAPIResource
)

data class FlavorText(
    val flavorText: String,
    val language: NamedAPIResource,
    val version: NamedAPIResource
)

data class GenerationGameIndex(
    val gameIndex: Int,
    val generation: NamedAPIResource
)

data class MachineVersionDetail(
    val machine: APIResource,
    val versionGroup: NamedAPIResource
)

data class Name(
    val name: String,
    val language: NamedAPIResource
)

data class VerboseEffect(
    val effect: String,
    val shortEffect: String,
    val language: NamedAPIResource
)

data class VersionEncounterDetail(
    val version: NamedAPIResource,
    val maxChance: Int,
    val encounterDetails: Encounter
)

data class VersionGameIndex(
    val gameIndex: Int,
    val version: NamedAPIResource
)

data class VersionGroupFlavorText(
    val text: String,
    val language: NamedAPIResource,
    val versionGroup: NamedAPIResource
)
