package com.dux.api_soccer_team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * SoccerTeam
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SoccerTeam {


  private Integer id;
  @NotNull(message = "Atributo nombre faltante")
  @NotBlank(message = "El nombre del equipo no puede estar vacio")
  private String nombre;
  @NotNull(message = "Atributo liga faltante")
  @NotBlank(message = "El nombre de la liga no puede estar vacio")
  private String liga;
  @NotNull(message = "Atributo pais faltante")
  @NotBlank(message = "El nombre del pais no puede estar vacio")
  private String pais;

  public SoccerTeam id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public SoccerTeam nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  
  @Schema(name = "nombre", example = "Real Madrid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nombre")
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public SoccerTeam liga(String liga) {
    this.liga = liga;
    return this;
  }

  /**
   * Get liga
   * @return liga
  */
  
  @Schema(name = "liga", example = "La liga", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("liga")
  public String getLiga() {
    return liga;
  }

  public void setLiga(String liga) {
    this.liga = liga;
  }

  public SoccerTeam pais(String pais) {
    this.pais = pais;
    return this;
  }

  /**
   * Get pais
   * @return pais
  */
  
  @Schema(name = "pais", example = "España", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pais")
  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoccerTeam soccerTeam = (SoccerTeam) o;
    return Objects.equals(this.id, soccerTeam.id) &&
        Objects.equals(this.nombre, soccerTeam.nombre) &&
        Objects.equals(this.liga, soccerTeam.liga) &&
        Objects.equals(this.pais, soccerTeam.pais);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, liga, pais);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SoccerTeam {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    liga: ").append(toIndentedString(liga)).append("\n");
    sb.append("    pais: ").append(toIndentedString(pais)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

